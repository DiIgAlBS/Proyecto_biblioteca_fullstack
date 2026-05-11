package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Tipo_Notificacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // @Data (Lombok) genera automáticamente por debajo los Getters, Setters, toString y constructores
public class NotificacionRequestDTO {

    @NotNull(message = "El ID de usuario es obligatorio") // Garantiza que el cliente envíe a quién va dirigida la notificación
    private Integer usuarioId;

    @NotBlank(message = "El mensaje no puede estar vacío") // Garantiza que el texto de la notificación exista y no sean puros espacios en blanco
    private String mensaje;

    @NotNull(message = "Debe especificar el tipo de notificación") // Garantiza que se seleccione una categoría de notificación (ej. SMS, EMAIL) válida
    private Tipo_Notificacion tipo;
}