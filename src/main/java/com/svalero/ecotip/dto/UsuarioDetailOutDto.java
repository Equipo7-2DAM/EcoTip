package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDetailOutDto {
    private long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;
    private boolean colaborador;
    private Float donativo;
    private List<AnimalOutDto> animales;
}
