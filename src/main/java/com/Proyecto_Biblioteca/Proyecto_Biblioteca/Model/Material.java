package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materiales")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Es obligatorio ingresar el nombre del material")
    @Size(min = 1, max = 50, message = "El nombre del material debe tener entre 1 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombreMaterial;

    @Column(name = "libro_id")
    private Integer libroId;
}
