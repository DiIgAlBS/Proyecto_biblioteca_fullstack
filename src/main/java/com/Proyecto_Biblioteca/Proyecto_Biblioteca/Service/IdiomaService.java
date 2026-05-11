package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.IdiomaDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Idioma;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.IdiomaRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    public List<IdiomaDTO> obtenerTodos() {
        log.info("Service: Listando todos los idiomas disponibles");
        return idiomaRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    public IdiomaDTO buscarPorId(Integer id) {
        log.info("Service: Buscando idioma con ID: {}", id);
        Idioma idioma = idiomaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Error: El idioma con ID " + id + " no existe."));
        return convertirADTO(idioma);
    }

    private IdiomaDTO convertirADTO(Idioma idioma) {
        return IdiomaDTO.builder()
            .id(idioma.getId())
            .nombreIdioma(idioma.getNombreIdioma())
            .build();
    }
}