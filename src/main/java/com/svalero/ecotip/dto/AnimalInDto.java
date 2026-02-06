package com.svalero.ecotip.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalInDto {
    private String nombre;
    private String especie;
    private float peso;
    private boolean EnPeligro;
    private LocalDate fechaAvistamiento;
    private  boolean apadrinado;
}
