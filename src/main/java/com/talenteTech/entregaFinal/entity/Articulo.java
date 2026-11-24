package com.talenteTech.entregaFinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "articulo")
public  class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre;
    private double precio;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


}
