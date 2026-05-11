package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.InventarioDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Inventario;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.InventarioRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioDTO> obtenerTodos() {
        log.info("Service: Listando todos los registros de inventario");
        return inventarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO guardar(InventarioDTO dto) {
        log.info("Service: Registrando nuevo movimiento en inventario");
        Inventario inventario = Inventario.builder()
                .servicioControlProductos(dto.getServicioControlProductos())
                .servicioComprasProveedores(dto.getServicioComprasProveedores())
                .servicioVentasPedidos(dto.getServicioVentasPedidos())
                .servicioStockAlmacen(dto.getServicioStockAlmacen())
                .build();

        return convertirADTO(inventarioRepository.save(inventario));
    }

    private InventarioDTO convertirADTO(Inventario i) {
        return InventarioDTO.builder()
                .id(i.getId())
                .servicioControlProductos(i.getServicioControlProductos())
                .servicioComprasProveedores(i.getServicioComprasProveedores())
                .servicioVentasPedidos(i.getServicioVentasPedidos())
                .servicioStockAlmacen(i.getServicioStockAlmacen())
                .build();
    }
}