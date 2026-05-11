package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.LibroDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Libro;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.LibroRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos con Streams
    public List<LibroDTO> obtenerTodos() {
        log.info("Service: Listando todos los libros");
        return libroRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    // Buscar por ID
    public LibroDTO buscarPorId(Integer id) {
        log.info("Service: Buscando libro ID: {}", id);
        Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El libro con ID " + id + " no existe."));
        return convertirADTO(libro);
    }

    public LibroDTO actualizar(Integer id, LibroDTO dto) {
        log.info("Service: Actualizando libro ID: {}", id);
        Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede actualizar: Libro no encontrado"));
        if (dto.getNombre() != null) libro.setNombre(dto.getNombre());
        if (dto.getFechaPublicacion() != null) libro.setFechaPublicacion(dto.getFechaPublicacion());
        if (dto.getIdiomaId() != null) libro.setIdiomaId(dto.getIdiomaId());
        if (dto.getAutorId() != null) libro.setAutorId(dto.getAutorId());
        return convertirADTO(libroRepository.save(libro));
    }

    private LibroDTO convertirADTO(Libro libro) {
        return LibroDTO.builder()
            .id(libro.getId())
            .nombre(libro.getNombre())
            .fechaPublicacion(libro.getFechaPublicacion())
            .idiomaId(libro.getIdiomaId())
            .autorId(libro.getAutorId())
            .editorialId(libro.getEditorialId())
            .categoriaId(libro.getCategoriaId())
            .build();
    }
}