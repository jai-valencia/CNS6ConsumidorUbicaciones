package com.transporte.ubicaciones.controller;

import com.transporte.ubicaciones.entity.UbicacionGPSEntity;
import com.transporte.ubicaciones.service.UbicacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {
    
    private static final Logger logger = LoggerFactory.getLogger(UbicacionController.class);
    
    private final UbicacionService ubicacionService;
    
    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }
    
    @GetMapping
    public ResponseEntity<List<UbicacionGPSEntity>> obtenerTodasUbicaciones() {
        logger.info("Consultando todas las ubicaciones");
        List<UbicacionGPSEntity> ubicaciones = ubicacionService.obtenerTodasLasUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }
    
    @GetMapping("/autobus/{idAutobus}")
    public ResponseEntity<List<UbicacionGPSEntity>> obtenerUbicacionesPorAutobus(
            @PathVariable String idAutobus) {
        logger.info("Consultando ubicaciones del autobús: {}", idAutobus);
        List<UbicacionGPSEntity> ubicaciones = ubicacionService.obtenerUbicacionesPorAutobus(idAutobus);
        return ResponseEntity.ok(ubicaciones);
    }
    
    @GetMapping("/ruta/{ruta}")
    public ResponseEntity<List<UbicacionGPSEntity>> obtenerUbicacionesPorRuta(
            @PathVariable String ruta) {
        logger.info("Consultando ubicaciones de la ruta: {}", ruta);
        List<UbicacionGPSEntity> ubicaciones = ubicacionService.obtenerUbicacionesPorRuta(ruta);
        return ResponseEntity.ok(ubicaciones);
    }
    
    @GetMapping("/autobus/{idAutobus}/rango")
    public ResponseEntity<List<UbicacionGPSEntity>> obtenerUbicacionesPorRango(
            @PathVariable String idAutobus,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        logger.info("Consultando ubicaciones del autobús {} entre {} y {}", 
                    idAutobus, fechaInicio, fechaFin);
        List<UbicacionGPSEntity> ubicaciones = ubicacionService.obtenerUbicacionesPorRangoFecha(
                idAutobus, fechaInicio, fechaFin);
        return ResponseEntity.ok(ubicaciones);
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "consumidor-ubicaciones");
        return ResponseEntity.ok(health);
    }
}
