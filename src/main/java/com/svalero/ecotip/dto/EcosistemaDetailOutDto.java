package com.svalero.ecotip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcosistemaDetailOutDto {
    private long id;
    private String nombre;
    private String descripcion;
    private Float temperaturaMedia;
    private List<AnimalOutDto> animales;
    private boolean isActive;
    private LocalDate createdAt;
}
