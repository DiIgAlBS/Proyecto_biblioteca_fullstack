package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Multa;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> { // Es una "interface", no una "class". Hereda todo el poder de JpaRepository.
    List<Multa> findByUsuarioId(Integer usuarioId); // Retorna todas las multas que le pertenezcan a un usuario específico.
}