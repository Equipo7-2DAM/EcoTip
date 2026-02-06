package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDetailOutDto {
    private String nombre;
    private String especie;
    private float peso;
    private boolean EnPeligro;
    private EcosistemaOutDto ecosistemaOutDto;
    private UsuarioOutDto usuarioOutDto;
    private LocalDate fechaAvistamiento;
}
