package com.transporte.ubicaciones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGPS implements Serializable {
    
    private String idAutobus;
    private Double latitud;
    private Double longitud;
    private String ruta;
    private Double velocidad;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHora;
    
    private String direccion;
    private String estado;
}
