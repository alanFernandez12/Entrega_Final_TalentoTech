package com.talenteTech.entregaFinal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticuloDTO {
    private Long codigo;
    private String nombre;
    private double precio;
    private Long categoriaId;
}
