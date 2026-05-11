package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.IdiomaDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service.IdiomaService;

@RestController
@RequestMapping("/api/v1/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaService idiomaService; // Nombre más limpio y estándar

    @GetMapping
    public ResponseEntity<List<IdiomaDTO>> todosLosIdiomas() {
        List<IdiomaDTO> idiomas = idiomaService.obtenerTodos();

        if (idiomas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(idiomas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            IdiomaDTO idioma = idiomaService.buscarPorId(id);
            return ResponseEntity.ok(idioma);
        } catch (RuntimeException e) {
            // Usamos un mensaje de error más claro en lugar de solo un 404 vacío
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
