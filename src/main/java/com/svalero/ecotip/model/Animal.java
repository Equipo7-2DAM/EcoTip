package com.svalero.ecotip.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecosistema_id")
    private Ecosistema ecosistema;*/


}
