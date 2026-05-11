package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByRut(String rut); // Opcional: Para validar duplicados antes de guardar
}