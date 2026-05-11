package com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO;

import java.time.LocalDate;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Estado_Multa;

import lombok.Builder;
import lombok.Data;

@Data // @Data (Lombok) genera automáticamente por debajo los Getters, Setters, toString y constructores
@Builder // @Builder (Lombok) habilita el patrón "Builder" para crear objetos de forma fluida paso a paso
public class MultaDTO {
    private Integer id;
    private Integer prestamoId;
    private Integer usuarioId;
    private Double monto;
    private String motivo;
    private LocalDate fechaGeneracion;
    private Estado_Multa estado;
}