package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private int intencionesApadrinar;
}
