package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MaterialDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Material;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.MaterialRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialDTO> obtenerTodos() {
        log.info("Service: Obteniendo lista completa de materiales");
        return materialRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    public MaterialDTO guardar(MaterialDTO dto) {
        log.info("Service: Guardando nuevo material: {}", dto.getNombreMaterial());
        Material material = Material.builder()
            .nombreMaterial(dto.getNombreMaterial())
            .libroId(dto.getLibroId()) 
            .build();
        return convertirADTO(materialRepository.save(material));
    }

    private MaterialDTO convertirADTO(Material material) {
        return MaterialDTO.builder()
            .id(material.getId())
            .nombreMaterial(material.getNombreMaterial())
            .libroId(material.getLibroId())
            .build();
    }
}
