package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MultaDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MultaRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service.MultaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController // Indica que esta clase recibe peticiones web HTTP y devuelve las respuestas en formato JSON
@RequestMapping("/api/v1/multas") // Define la ruta base en el navegador/Postman para acceder a las multas
@Slf4j // Habilita la variable "log" para registrar eventos en la consola (Trazabilidad)
public class MultaController {

    @Autowired // Inyecta automáticamente el Servicio (lógica de negocio) sin tener que usar "new"
    private MultaService multaService;

    @GetMapping // Responde a peticiones HTTP GET (Consultar información)
    public ResponseEntity<List<MultaDTO>> listar() {
        log.info("REST Request: Solicitud para listar todas las multas");
        return ResponseEntity.ok(multaService.obtenerTodas()); // Retorna la lista de multas con un código de éxito HTTP 200 (OK)
    }

    @PostMapping // Responde a peticiones HTTP POST (Crear/Guardar nueva información)
    public ResponseEntity<MultaDTO> crear(@Valid @RequestBody MultaRequestDTO request) { // @Valid activa las reglas del DTO. @RequestBody convierte el JSON a un objeto Java
        log.info("REST Request: Creando nueva multa para el usuario ID: {}", request.getUsuarioId());
        return new ResponseEntity<>(multaService.crearMulta(request), HttpStatus.CREATED); // Retorna la nueva multa creada con un código de éxito HTTP 201 (CREATED)
    }
}