package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
    private Integer id;
    private String nombreMaterial;
    private String tipoMaterial; // Ej: Digital, Físico, Audiovisual
    private String estado;
    private Integer libroId;       // Ej: Disponible, En mantenimiento
}