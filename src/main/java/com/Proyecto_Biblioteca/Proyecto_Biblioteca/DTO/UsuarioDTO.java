package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String correo;
    private Integer numero;
    private List<Integer> librosIds; 
    private List<String> nombresLibros;
}
