package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdiomaDTO {
    private Integer id;
    private String nombreIdioma;
    private String codigoIso; // Ej: "ES", "EN", "FR"
}