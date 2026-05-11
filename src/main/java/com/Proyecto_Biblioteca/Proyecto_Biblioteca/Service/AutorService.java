package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.AutorDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Autor;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.AutorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> obtenerTodos() {
        log.info("Service: Obteniendo lista de autores");
        return autorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AutorDTO buscarPorId(Integer id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return mapToDTO(autor);
    }

    public AutorDTO guardar(AutorDTO dto) {
        log.info("Service: Guardando nuevo autor: {}", dto.getNombre());
        Autor autor = Autor.builder()
                .rut(dto.getRut())
                .correo(dto.getCorreo())
                .nombre(dto.getNombre())
                .numero(dto.getNumero())
                .ubicacion(dto.getUbicacion())
                .nacionalidad(dto.getNacionalidad())
                .build();
        return mapToDTO(autorRepository.save(autor));
    }

    private AutorDTO mapToDTO(Autor a) {
        return AutorDTO.builder()
                .id(a.getId())
                .rut(a.getRut())
                .correo(a.getCorreo())
                .nombre(a.getNombre())
                .numero(a.getNumero())
                .ubicacion(a.getUbicacion())
                .nacionalidad(a.getNacionalidad())
                .build();
    }
}
