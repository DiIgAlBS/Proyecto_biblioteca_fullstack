package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{ // Al heredar de JpaRepository, obtiene automáticamente los comandos básicos (guardar, buscar, eliminar)
}