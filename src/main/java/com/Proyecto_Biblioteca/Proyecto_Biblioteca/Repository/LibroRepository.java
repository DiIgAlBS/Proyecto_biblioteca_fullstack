package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> findByNombreContainingIgnoreCase(String nombre);
    List<Libro> findByIdiomaId(Integer idiomaId);
}
