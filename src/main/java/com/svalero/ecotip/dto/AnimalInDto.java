package com.svalero.ecotip.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "Weight is mandatory")
    @Positive(message = "Weight needs to be a positive number")
    private float peso;
    @NotNull(message = "In danger property is mandatory")
    private boolean EnPeligro;
    @PastOrPresent(message = "Fecha must be in the past")
    private LocalDate fechaAvistamiento;
    @NotNull(message = "Apadrinado condition is mandatory")
    private boolean apadrinado;
    @NotNull(message = "Ecosystem id is mandatory")
    private long ecosistemaId;
}
