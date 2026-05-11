package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.EditorialDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Editorial;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.EditorialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public List<EditorialDTO> obtenerTodas() {
        log.info("Service: Listando todas las editoriales");
        return editorialRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EditorialDTO buscarPorId(Integer id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
        return mapToDTO(editorial);
    }

    public EditorialDTO guardar(EditorialDTO dto) {
        log.info("Service: Registrando editorial: {}", dto.getNombre());
        Editorial editorial = Editorial.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .correoContacto(dto.getCorreoContacto())
                .build();
        return mapToDTO(editorialRepository.save(editorial));
    }

    private EditorialDTO mapToDTO(Editorial e) {
        return EditorialDTO.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .direccion(e.getDireccion())
                .telefono(e.getTelefono())
                .correoContacto(e.getCorreoContacto())
                .build();
    }
}