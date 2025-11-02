package com.adoo.album.infrastructure.persistence.repository;

import com.adoo.album.model.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {

    // Método para obtener el total de usuarios distintos que han comprado un paquete.
    // Esto es el denominador para la Tasa de Apertura.
    @Query("SELECT COUNT(DISTINCT p.user.id) FROM Pack p")
    long countDistinctUserIds();

}