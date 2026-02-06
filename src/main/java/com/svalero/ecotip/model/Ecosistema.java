package com.svalero.ecotip.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity (name = "ecosistemas")
@Table(name = "ecosistemas")
public class Ecosistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column(name = "temperatura_media")
    private Float temperaturaMedia;
    @Column
    private boolean active;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "ecosistema")
    private List<Animal> animales;
}
