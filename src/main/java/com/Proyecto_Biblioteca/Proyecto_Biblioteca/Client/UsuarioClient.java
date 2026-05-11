package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms_usuarios", url = "http:localhost:8081") // Activa este archivo como cliente HTTP para conectarse al microservicio "microservicio de usuarios"
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/existe/{id}") // Define la ruta exacta del otro microservicio a la que vamos a consultar (Método GET)
    Boolean verificarSiExiste(@PathVariable("id") Integer id); // Inyecta el "id" en la URL y retorna Verdadero o Falso si el usuario existe
}