package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.NotificacionDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.NotificacionRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Notificacion;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.NotificacionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j // @Slf4j permite usar "log.info" para imprimir mensajes en la consola del servidor
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<NotificacionDTO> obtenerTodas() { // Trae absolutamente todas las notificaciones que existen en el sistema
        log.info("Service: Obteniendo el registro general de notificaciones");
        return notificacionRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public List<NotificacionDTO> listarPorUsuario(Integer usuarioId) { // Trae solo las notificaciones de un alumno o bibliotecario específico
        log.info("Service: Ejecutando query para obtener notificaciones del usuario {}", usuarioId);
        return notificacionRepository.findByUsuarioId(usuarioId).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public NotificacionDTO crearNotificacion(NotificacionRequestDTO request) { // Crea un aviso nuevo y lo guarda en la base de datos
        log.info("Service: Generando notificación en base de datos. Mensaje: '{}'", request.getMensaje());
        Notificacion notificacion = Notificacion.builder()
            .usuarioId(request.getUsuarioId())
            .mensaje(request.getMensaje())
            .tipo(request.getTipo())
            .fechaEnvio(LocalDate.now()) // El sistema marca automáticamente la fecha de hoy
            .leida(false) // Por defecto, toda notificación llega como "no leída"
            .build();
        Notificacion guardada = notificacionRepository.save(notificacion);
        log.info("Service: Notificación {} enviada con éxito.", guardada.getId());
        return mapToDTO(guardada);
    }

    private NotificacionDTO mapToDTO(Notificacion n) { //Este es el "traductor": convierte la Notificación de la base de datos al formato DTO para que el Frontend la pueda mostrar.
        return NotificacionDTO.builder()
            .id(n.getId())
            .usuarioId(n.getUsuarioId())
            .mensaje(n.getMensaje())
            .fechaEnvio(n.getFechaEnvio())
            .leida(n.getLeida())
            .tipo(n.getTipo())
            .build();
    }
}