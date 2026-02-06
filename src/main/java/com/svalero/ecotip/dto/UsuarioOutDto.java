package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOutDto {
    private long id;
    private String nombre;
    private String apellidos;
}
