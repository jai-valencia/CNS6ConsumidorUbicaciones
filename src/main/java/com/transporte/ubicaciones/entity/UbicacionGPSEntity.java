package com.transporte.ubicaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "UBICACIONES_GPS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGPSEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ubicacion_seq")
    @SequenceGenerator(name = "ubicacion_seq", sequenceName = "UBICACION_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "ID_AUTOBUS", nullable = false, length = 50)
    private String idAutobus;
    
    @Column(name = "LATITUD", nullable = false)
    private Double latitud;
    
    @Column(name = "LONGITUD", nullable = false)
    private Double longitud;
    
    @Column(name = "RUTA", nullable = false, length = 100)
    private String ruta;
    
    @Column(name = "VELOCIDAD", nullable = false)
    private Double velocidad;
    
    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "DIRECCION", length = 200)
    private String direccion;
    
    @Column(name = "ESTADO", length = 50)
    private String estado;
    
    @Column(name = "FECHA_REGISTRO", nullable = false)
    private LocalDateTime fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
