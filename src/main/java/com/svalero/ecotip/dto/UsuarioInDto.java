package com.svalero.ecotip.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInDto {
    @NotBlank(message = "Name is mandatory")
    private String nombre;
    @NotBlank(message = "Last Name is mandatory")
    private String apellidos;
    @NotBlank(message = "DNI is mandatory")
    @Pattern(regexp = "[0-9]{8}[A-Z]", message = "Invalid DNI Format")
    private String dni;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Past(message = "Date has to be in the past")
    private LocalDate fechaNacimiento;
    private boolean colaborador;
    @NotNull(message = "n")
    private Float donativo;
    @NotNull(message = "animal_id")
    private long animalId;

}
