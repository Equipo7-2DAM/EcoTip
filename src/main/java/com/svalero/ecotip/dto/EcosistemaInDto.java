package com.svalero.ecotip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcosistemaInDto {
    @NotBlank(message = "Name is mandatory")
    private String nombre;
    @NotBlank(message = "Descripcion is mandatory")
    private String descripcion;
    @NotNull(message = "Average Temperature is mandatory")
    private Float temperaturaMedia;
    @NotNull(message = "Active property is mandatory")
    private boolean active;
    @NotNull(message = "Date is mandatory")
    @PastOrPresent
    private LocalDate createdAt;
}
