package com.svalero.ecotip.controller;

import com.svalero.ecotip.dto.*;
import com.svalero.ecotip.exception.EcosistemaNotFoundException;
import com.svalero.ecotip.exception.ErrorResponse;
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
@RequestMapping("/ecosistemas")
public class EcosistemaController {

    @Autowired
    private EcosistemaService ecosistemaService;


    @GetMapping("")
    public ResponseEntity<List<EcosistemaOutDto>> getAll() {
        List<EcosistemaOutDto> ecosistemaOutDtos = ecosistemaService.findAll();
        return ResponseEntity.ok(ecosistemaOutDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcosistemaDetailOutDto> getEcosistemaById(@PathVariable long id) throws EcosistemaNotFoundException {
        return ResponseEntity.ok(ecosistemaService.findById(id));
    }


    @PostMapping("")
    public ResponseEntity<EcosistemaDetailOutDto> addEcosistema(@Valid @RequestBody EcosistemaInDto ecosistemaInDto) {

        EcosistemaDetailOutDto nuevoEcosistema = ecosistemaService.add(ecosistemaInDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEcosistema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EcosistemaDetailOutDto> modifyEcosistema(@Valid @RequestBody EcosistemaInDto ecosistemaInDto, @PathVariable long id) throws EcosistemaNotFoundException {

        EcosistemaDetailOutDto ecosistemaUpdated = ecosistemaService.modify(id, ecosistemaInDto);

        return ResponseEntity.ok(ecosistemaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcosistema(@PathVariable long id) throws EcosistemaNotFoundException {
        ecosistemaService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(EcosistemaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EcosistemaNotFoundException enfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Ecosystem not found");
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
