package com.svalero.ecotip.controller;

import com.svalero.ecotip.dto.UsuarioDetailOutDto;
import com.svalero.ecotip.dto.UsuarioInDto;
import com.svalero.ecotip.dto.UsuarioOutDto;
import com.svalero.ecotip.exception.ErrorResponse;
import com.svalero.ecotip.exception.UsuarioNotFoundException;
import com.svalero.ecotip.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<UsuarioOutDto>> getAll() {
        List<UsuarioOutDto> usuarioOutDtos = usuarioService.findAll();
        return ResponseEntity.ok(usuarioOutDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetailOutDto> getUsuarioById(@PathVariable long id) throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.findById(id));
    }


    @PostMapping("")
    public ResponseEntity<UsuarioDetailOutDto> addUsuario(@Valid @RequestBody UsuarioInDto usuarioInDto)  {

        UsuarioDetailOutDto nuevoUsuario = usuarioService.add(usuarioInDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetailOutDto> modifyUsuario(@Valid @RequestBody UsuarioInDto usuarioInDto, @PathVariable long id) throws UsuarioNotFoundException {

        UsuarioDetailOutDto usuarioUpdated = usuarioService.modify(id, usuarioInDto);

        return ResponseEntity.ok(usuarioUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable long id) throws UsuarioNotFoundException {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UsuarioNotFoundException unfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("User not found");
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
