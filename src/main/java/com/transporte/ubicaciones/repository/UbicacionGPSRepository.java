package com.transporte.ubicaciones.repository;

import com.transporte.ubicaciones.entity.UbicacionGPSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UbicacionGPSRepository extends JpaRepository<UbicacionGPSEntity, Long> {
    
    List<UbicacionGPSEntity> findByIdAutobus(String idAutobus);
    
    List<UbicacionGPSEntity> findByRuta(String ruta);
    
    @Query("SELECT u FROM UbicacionGPSEntity u WHERE u.idAutobus = :idAutobus " +
           "AND u.fechaHora BETWEEN :fechaInicio AND :fechaFin ORDER BY u.fechaHora DESC")
    List<UbicacionGPSEntity> findByAutobusAndFechaRange(
            @Param("idAutobus") String idAutobus,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
    
    @Query("SELECT u FROM UbicacionGPSEntity u WHERE u.idAutobus = :idAutobus " +
           "ORDER BY u.fechaHora DESC LIMIT 1")
    UbicacionGPSEntity findUltimaUbicacionByAutobus(@Param("idAutobus") String idAutobus);
}
