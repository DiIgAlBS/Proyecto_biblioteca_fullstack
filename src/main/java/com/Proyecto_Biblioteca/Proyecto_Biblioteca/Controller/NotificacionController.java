package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.NotificacionDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.NotificacionRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service.NotificacionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController // Controlador REST: gestiona peticiones HTTP y devuelve respuestas en JSON
@RequestMapping("/api/v1/notificaciones") // Ruta base centralizada para este microservicio
@Slf4j // Activa la variable "log" para dejar rastro de los eventos en consola
public class NotificacionController {

    @Autowired // Inyecta el Servicio (la lógica) automáticamente, sin usar "new"
    private NotificacionService notificacionService;

    @GetMapping // Responde a GET en la ruta base (trae todas las notificaciones de la BD)
    public ResponseEntity<List<NotificacionDTO>> listarTodas() {
        log.info("REST Request: Solicitud para listar todas las notificaciones del sistema");
        return ResponseEntity.ok(notificacionService.obtenerTodas()); // Devuelve la lista completa con código HTTP 200 (OK)
    }

    @GetMapping("/usuario/{usuarioId}") // ¡NUEVO! Sub-ruta dinámica. Responde a GET ej: /api/v1/notificaciones/usuario/5
    public ResponseEntity<List<NotificacionDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { // @PathVariable extrae el valor "{usuarioId}" de la URL y lo inyecta en la variable de Java
        log.info("REST Request: Buscando notificaciones para el usuario ID: {}", usuarioId);
        return ResponseEntity.ok(notificacionService.listarPorUsuario(usuarioId)); // Devuelve la lista filtrada con código HTTP 200 (OK)
    }

    @PostMapping // Responde a POST para crear/enviar una nueva notificación
    public ResponseEntity<NotificacionDTO> enviar(@Valid @RequestBody NotificacionRequestDTO request) { // @Valid aplica las reglas del DTO. @RequestBody convierte el texto JSON a Objeto Java
        log.info("REST Request: Solicitud de envío de notificación tipo [{}] al usuario ID: {}", request.getTipo(), request.getUsuarioId());
        return new ResponseEntity<>(notificacionService.crearNotificacion(request), HttpStatus.CREATED); // Devuelve la notificación creada con código HTTP 201 (CREATED)
    }
}