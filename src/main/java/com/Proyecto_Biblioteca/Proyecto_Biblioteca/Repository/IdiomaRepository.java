package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Idioma;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Integer> {
    // Hereda todos los métodos CRUD (save, findAll, findById, etc.)
}