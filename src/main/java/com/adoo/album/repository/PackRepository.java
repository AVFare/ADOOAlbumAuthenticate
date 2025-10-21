package com.adoo.album.repository;

import com.adoo.album.model.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {

    // RF10 (Reporte de Uso): Tasa de Apertura de Paquetes
    // Obtener el total de paquetes comprados por un usuario
    long countByUserId(Long userId);

    // Obtener el total de paquetes comprados en total
    // El método count() de JpaRepository ya hace esto, pero aquí lo especificamos:
    // long count();
}