package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuarioId(Integer usuarioId); // Devuelve el historial completo (leídas y no leídas) de un usuario
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Integer usuarioId); // Trae solamente las notificaciones nuevas (las que el usuario todavía no lee)
}