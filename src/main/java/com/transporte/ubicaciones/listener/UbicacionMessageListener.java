package com.transporte.ubicaciones.listener;

import com.transporte.ubicaciones.model.UbicacionGPS;
import com.transporte.ubicaciones.service.UbicacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UbicacionMessageListener {
    
    private static final Logger logger = LoggerFactory.getLogger(UbicacionMessageListener.class);
    
    private final UbicacionService ubicacionService;
    
    public UbicacionMessageListener(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }
    
    @RabbitListener(queues = "${rabbitmq.queue.ubicaciones}")
    public void recibirMensajeUbicacion(UbicacionGPS ubicacion) {
        try {
            logger.info("Mensaje recibido de RabbitMQ - Autobús: {}", ubicacion.getIdAutobus());
            logger.debug("Datos completos: {}", ubicacion);
            
            ubicacionService.guardarUbicacion(ubicacion);
            
            logger.info("Mensaje procesado y guardado exitosamente");
        } catch (Exception e) {
            logger.error("Error al procesar mensaje de ubicación: {}", e.getMessage(), e);
            // Aquí podrías implementar lógica de reintento o enviar a una cola de errores
            throw new RuntimeException("Error al procesar mensaje: " + e.getMessage(), e);
        }
    }
}
