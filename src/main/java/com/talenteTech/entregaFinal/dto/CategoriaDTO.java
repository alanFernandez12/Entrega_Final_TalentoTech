package com.talenteTech.entregaFinal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
