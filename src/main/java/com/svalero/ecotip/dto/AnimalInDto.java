package com.svalero.ecotip.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalInDto {
    @NotBlank(message = "Name is mandatory")
    private String nombre;
    @NotBlank(message = "Specie is mandatory")
    private String especie;
    @NotBlank(message = "Weight is mandatory")
    private float peso;
    @NotBlank(message = "In danger property is mandatory")
    private boolean EnPeligro;
    @NotBlank(message = "Fecha is mandatory")
    private LocalDate fechaAvistamiento;
    @NotBlank(message = "Apadrinado condition is mandatory")
    private  boolean apadrinado;
}
