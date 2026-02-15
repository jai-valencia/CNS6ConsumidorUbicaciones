package com.transporte.ubicaciones.service;

import com.transporte.ubicaciones.entity.UbicacionGPSEntity;
import com.transporte.ubicaciones.model.UbicacionGPS;
import com.transporte.ubicaciones.repository.UbicacionGPSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UbicacionService {
    
    private static final Logger logger = LoggerFactory.getLogger(UbicacionService.class);
    
    private final UbicacionGPSRepository repository;
    
    public UbicacionService(UbicacionGPSRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    public void guardarUbicacion(UbicacionGPS ubicacion) {
        try {
            logger.info("Guardando ubicación del autobús {} en Oracle Cloud", ubicacion.getIdAutobus());
            
            UbicacionGPSEntity entity = new UbicacionGPSEntity();
            entity.setIdAutobus(ubicacion.getIdAutobus());
            entity.setLatitud(ubicacion.getLatitud());
            entity.setLongitud(ubicacion.getLongitud());
            entity.setRuta(ubicacion.getRuta());
            entity.setVelocidad(ubicacion.getVelocidad());
            entity.setFechaHora(ubicacion.getFechaHora());
            entity.setDireccion(ubicacion.getDireccion());
            entity.setEstado(ubicacion.getEstado());
            
            repository.save(entity);
            
            logger.info("Ubicación guardada exitosamente con ID: {}", entity.getId());
        } catch (Exception e) {
            logger.error("Error al guardar ubicación en la base de datos: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar ubicación: " + e.getMessage(), e);
        }
    }
    
    public List<UbicacionGPSEntity> obtenerUbicacionesPorAutobus(String idAutobus) {
        return repository.findByIdAutobus(idAutobus);
    }
    
    public List<UbicacionGPSEntity> obtenerUbicacionesPorRuta(String ruta) {
        return repository.findByRuta(ruta);
    }
    
    public List<UbicacionGPSEntity> obtenerTodasLasUbicaciones() {
        return repository.findAll();
    }
    
    public List<UbicacionGPSEntity> obtenerUbicacionesPorRangoFecha(
            String idAutobus, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return repository.findByAutobusAndFechaRange(idAutobus, fechaInicio, fechaFin);
    }
}
