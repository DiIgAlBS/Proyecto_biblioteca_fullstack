package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Controller;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.PrestamoDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.PrestamoRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RestController // Controlador REST que gestiona las peticiones web y responde en formato JSON
@RequestMapping("/api/v1/prestamos") // Define la URL base para el módulo de préstamos
@Slf4j // Habilita el registro de eventos (trazabilidad) en la consola
public class PrestamoController {

    @Autowired // Inyecta automáticamente la capa de servicio (lógica de negocio)
    private PrestamoService prestamoService;

    @GetMapping // Responde a peticiones GET para obtener la lista de préstamos
    public ResponseEntity<List<PrestamoDTO>> listar() {
        List<PrestamoDTO> lista = prestamoService.obtenerTodos();
        if (lista.isEmpty()) {  // Responde a peticiones GET para obtener la lista de préstamos
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna HTTP 204 (No Content) porque la petición fue exitosa pero no hay datos
        }
        return new ResponseEntity<>(lista, HttpStatus.OK); // Si hay datos, retorna la lista con HTTP 200 (OK)
    }

    @PostMapping // Responde a peticiones POST para registrar un nuevo préstamo. Usa <?> (comodín) porque puede devolver un PrestamoDTO (éxito) o un String (error)
    public ResponseEntity<?> guardar(@Valid @RequestBody PrestamoRequestDTO request) {
        try { // El bloque try-catch atrapa cualquier error para que el sistema no se caiga
            log.info("REST Request: Recibiendo solicitud para crear un nuevo préstamo para el usuario ID: {}", request.getUsuarioId());
            PrestamoDTO nuevo = prestamoService.crearPrestamo(request); // Intenta procesar y guardar el préstamo en la base de datos
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED); // Si todo sale bien, retorna el nuevo préstamo con HTTP 201 (CREATED)
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Si algo falla (ej. el usuario no existe), retorna el mensaje de error con HTTP 400 (BAD REQUEST)
        }
    }
}