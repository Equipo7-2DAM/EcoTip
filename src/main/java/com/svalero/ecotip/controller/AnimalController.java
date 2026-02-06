package com.svalero.ecotip.controller;

import com.svalero.ecotip.dto.AnimalDetailOutDto;
import com.svalero.ecotip.dto.AnimalInDto;
import com.svalero.ecotip.dto.AnimalOutDto;
import com.svalero.ecotip.exception.AnimalNotFoundException;
import com.svalero.ecotip.exception.EcosistemaNotFoundException;
import com.svalero.ecotip.exception.ErrorResponse;
import com.svalero.ecotip.service.AnimalService;
import com.svalero.ecotip.service.EcosistemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private EcosistemaService ecosistemaService;

    @GetMapping("")
    public ResponseEntity<List<AnimalOutDto>> getAll() {
        List<AnimalOutDto> animalOutDtos = animalService.findAll();
        return ResponseEntity.ok(animalOutDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDetailOutDto> getAnimalById(@PathVariable long id) throws AnimalNotFoundException {
        return ResponseEntity.ok(animalService.findById(id));
    }


    @PostMapping("")
    public ResponseEntity<AnimalDetailOutDto> addAnimal(@Valid @RequestBody AnimalInDto animalInDto) throws EcosistemaNotFoundException {

        AnimalDetailOutDto nuevoAnimal = animalService.add(animalInDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDetailOutDto> modifyAnimal(@Valid @RequestBody AnimalInDto animalInDto, @PathVariable long id) throws AnimalNotFoundException {

        AnimalDetailOutDto animalUpdated = animalService.modify(id, animalInDto);

        return ResponseEntity.ok(animalUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) throws AnimalNotFoundException {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AnimalNotFoundException anfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Animal not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EcosistemaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EcosistemaNotFoundException enfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Ecosistema not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException manve){
        Map<String,String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
