package com.Proyecto_Biblioteca.Proyecto_Biblioteca.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MultaDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.DTO.MultaRequestDTO;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Estado_Multa;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Model.Multa;
import com.Proyecto_Biblioteca.Proyecto_Biblioteca.Repository.MultaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j // @Slf4j permite usar "log.info" para imprimir mensajes en la consola del servidor
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    public List<MultaDTO> obtenerTodas() {
        log.info("Service: Consultando la base de datos para obtener todas las multas");
        return multaRepository.findAll().stream() // Buscamos todas las entidades, las convertimos una por una a DTO y las enviamos
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public MultaDTO crearMulta(MultaRequestDTO request) {
        log.info("Service: Preparando datos para nueva multa. Motivo: '{}', Monto: {}", request.getMotivo(), request.getMonto());
        Multa multa = Multa.builder() // Transformamos el Request (lo que llega de internet) en una Entidad (lo que entiende la BD)
            .prestamoId(request.getPrestamoId())
            .usuarioId(request.getUsuarioId())
            .monto(request.getMonto())
            .motivo(request.getMotivo())
            .fechaGeneracion(LocalDate.now()) // El servidor pone la fecha, no el usuario
            .estado(Estado_Multa.PENDIENTE) // Toda multa nueva nace como Pendiente
            .build();
        Multa multaGuardada = multaRepository.save(multa);
        log.info("Service: ¡Multa guardada exitosamente con ID {}!", multaGuardada.getId());
        
        return mapToDTO(multaGuardada);
    }

    private MultaDTO mapToDTO(Multa m) {
        return MultaDTO.builder() // Este método es como un "traductor": Toma la información como viene de la Base de Datos (Multa) y la pasa al formato que el cliente/usuario puede ver (MultaDTO)
            .id(m.getId())
            .prestamoId(m.getPrestamoId())
            .usuarioId(m.getUsuarioId())
            .monto(m.getMonto())
            .motivo(m.getMotivo())
            .fechaGeneracion(m.getFechaGeneracion())
            .estado(m.getEstado())
            .build();
    }
}