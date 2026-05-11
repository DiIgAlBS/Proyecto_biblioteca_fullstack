package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MaterialDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service.MaterialService;

@RestController
@RequestMapping("/api/v1/materiales")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> todosLosMateriales() {
        List<MaterialDTO> materiales = materialService.obtenerTodos();
        if (materiales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(materiales);
    }
}
