package com.adoo.album.repository;

import com.adoo.album.model.entity.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {

    // RF2: Obtener figuritas por álbum
    List<Sticker> findByAlbumId(Long albumId);

    // RF10 (Reporte de Rareza): Figuritas más raras (menor stock y mayor rareza)
    // Ordena por Rareza descendente (ÉPICA > RARA > COMÚN) y luego por Stock Disponible ascendente
    List<Sticker> findTop10ByOrderByRarezaDescStockDisponibleAsc();

    // Consulta para obtener una figurita aleatoria (necesario para el Paquete)
    // Esto se implementaría mejor con lógica en el Service, pero es una opción de repositorio
    // @Query("SELECT s FROM Sticker s WHERE s.album.id = :albumId ORDER BY FUNCTION('RAND')")
    // List<Sticker> findRandomStickersByAlbumId(@Param("albumId") Long albumId, Pageable pageable);
}