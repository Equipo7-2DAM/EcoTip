package com.svalero.ecotip.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animales")
@Table(name = "animales")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String especie;
    @Column
    private float peso;
    @Column(name = "en_peligro")
    private boolean EnPeligro;
    @Column(name = "fecha_avistamiento")
    private LocalDate fechaAvistamiento;
    @Column
    private  boolean apadrinado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecosistema_id")
    private Ecosistema ecosistema;

    @ManyToMany
    @JoinTable(
            name = "animales_apadrinados",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();


}
