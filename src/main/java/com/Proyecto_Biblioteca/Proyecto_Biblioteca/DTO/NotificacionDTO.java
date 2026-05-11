package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import java.time.LocalDate;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Tipo_Notificacion;

import lombok.Builder;
import lombok.Data;

@Data // @Data (Lombok) genera automáticamente por debajo los Getters, Setters, toString y constructores
@Builder // @Builder (Lombok) habilita el patrón "Builder" para crear objetos de forma fluida paso a paso
public class NotificacionDTO {
    private Integer id;
    private Integer usuarioId;
    private String mensaje;
    private LocalDate fechaEnvio;
    private Boolean leida;
    private Tipo_Notificacion tipo;
}