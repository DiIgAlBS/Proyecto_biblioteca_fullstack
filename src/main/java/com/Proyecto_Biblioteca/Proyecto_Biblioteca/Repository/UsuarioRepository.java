package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    Optional<Usuario> findByCorreo(String correo);
}