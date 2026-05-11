package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}