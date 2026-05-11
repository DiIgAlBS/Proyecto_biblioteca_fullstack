package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.UsuarioDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Usuario;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos con Streams (Tu estándar de calidad)
    public List<UsuarioDTO> obtenerTodos() {
        log.info("Service: Consultando lista completa de usuarios");
        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID con manejo de excepciones
    public UsuarioDTO buscarPorId(Integer id) {
        log.info("Service: Buscando usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return convertirADTO(usuario);
    }

    public UsuarioDTO actualizar(Integer id, UsuarioDTO dto) {
        log.info("Service: Actualizando datos del usuario ID: {}", id);
        Usuario user = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡El usuario no existe en los registros!"));
        if (dto.getNombre() != null) user.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) user.setCorreo(dto.getCorreo());
        if (dto.getNumero() != null) user.setNumero(dto.getNumero());

        return convertirADTO(usuarioRepository.save(user));
    }

    public String eliminar(Integer id) {
        log.warn("Service: Intentando eliminar usuario ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El usuario con ID " + id + " no existe."));

        String nombre = usuario.getNombre();
        usuarioRepository.delete(usuario);
        return "El usuario '" + nombre + "' ha sido eliminado exitosamente.";
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        return UsuarioDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .correo(usuario.getCorreo())
            .numero(usuario.getNumero())
            .build();
    }
}