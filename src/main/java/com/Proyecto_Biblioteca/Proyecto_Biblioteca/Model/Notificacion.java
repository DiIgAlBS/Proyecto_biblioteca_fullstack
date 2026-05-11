package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Le dice a Hibernate que esta clase es una tabla de base de datos
@Table(name = "notificaciones") // Nombra la tabla explícitamente como "notificaciones"
@Data // @Data (Lombok) genera automáticamente por debajo los Getters, Setters, toString y constructores
@Builder // @Builder (Lombok) habilita el patrón "Builder" para crear objetos de forma fluida paso a paso
@AllArgsConstructor // @AllArgsConstructor (Lombok): Crea un constructor con todos los atributos de la clase
@NoArgsConstructor // @NoArgsConstructor (Lombok): Crea un constructor vacío (obligatorio para que Spring trabaje bien)
public class Notificacion {

    @Id // Define este campo como la Llave Primaria (Primary Key) de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable gestionado por la base de datos (1, 2, 3...)
    private Integer id;

    @Column(nullable = false) 
    private Integer usuarioId;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @Column(nullable = false)
    private Boolean leida; // Para saber si el usuario ya vio el aviso

    @Enumerated(EnumType.STRING) // Guarda el Enum como texto (ej. "ALERTA", "MULTA") y no como un número
    @Column(nullable = false)
    private Tipo_Notificacion tipo;
}