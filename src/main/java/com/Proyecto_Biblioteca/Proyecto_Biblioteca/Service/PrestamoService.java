package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.PrestamoDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.PrestamoRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Prestamo;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Estado_Prestamo; // <-- ¡Ojo aquí con el nombre de tu archivo!
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.PrestamoRepository;

import lombok.extern.slf4j.Slf4j;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired // Este es el "cliente" que nos permite hablar con el Microservicio de Usuarios
    private UsuarioClient usuarioClient;

    public List<PrestamoDTO> obtenerTodos() { // Lista todos los préstamos que existen en la base de datos
        return prestamoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PrestamoDTO crearPrestamo(PrestamoRequestDTO request) { // Registra un nuevo préstamo en el sistema 
        log.info("Service: Iniciando validación y registro de préstamo de libro ID: {}", request.getLibroId());
        Prestamo prestamo = Prestamo.builder() // Construimos el objeto que se guardará en la base de datos
            .usuarioId(request.getUsuarioId())
            .libroId(request.getLibroId())
            .fechaPrestamo(LocalDate.now()) // Fecha de hoy automática
            .fechaDevolucion(request.getFechaDevolucion()) // Fecha que eligió el usuario
            .estado(Estado_Prestamo.ACTIVO) // El préstamo inicia como ACTIVO
            .build();
        log.info("Service: ¡Préstamo registrado exitosamente en la base de datos!");
        return convertirADTO(prestamoRepository.save(prestamo)); // Guardamos y el resultado lo "traducimos" a DTO para responder
    }

    private PrestamoDTO convertirADTO(Prestamo p) { // Nuestro "traductor": de formato Base de Datos a formato de Usuario (DTO)
        return PrestamoDTO.builder()
            .id(p.getId())
            .usuarioId(p.getUsuarioId())
            .libroId(p.getLibroId())
            .fechaPrestamo(p.getFechaPrestamo())
            .fechaDevolucion(p.getFechaDevolucion())
            .estado(p.getEstado())
            .build();
    }
}