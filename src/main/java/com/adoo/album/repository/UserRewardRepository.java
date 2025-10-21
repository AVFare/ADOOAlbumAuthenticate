package com.adoo.album.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.adoo.album.model.entity.UserReward;

public interface UserRewardRepository extends JpaRepository<UserReward, Long> {

    // QUERY CLAVE PARA EL REPORTE DE COMPLETITUD
    @Query("SELECT ur.album.titulo, COUNT(ur.id) " +
           "FROM UserReward ur " +
           "WHERE ur.estado = 'RECLAMADO' " +
           "GROUP BY ur.album.titulo " +
           "ORDER BY COUNT(ur.id) DESC")
    List<Object[]> countCompletedAlbumsByAlbum();
}