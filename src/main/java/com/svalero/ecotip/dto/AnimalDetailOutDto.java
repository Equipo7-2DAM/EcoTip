package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDetailOutDto {
    private long id;
    private String nombre;
    private String especie;
    private float peso;
    private boolean EnPeligro;
    private EcosistemaOutDto ecosistema;
    private List<UsuarioOutDto> usuarios;
    private LocalDate fechaAvistamiento;
}
