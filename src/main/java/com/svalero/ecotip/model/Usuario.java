package com.svalero.ecotip.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "usuarios")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;
    @Column
    private String email;
    @Column
    private boolean colaborador;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column
    private Float donativo;


    @ManyToMany(mappedBy = "usuarios")
    private List<Animal> animales = new ArrayList<>();
}
