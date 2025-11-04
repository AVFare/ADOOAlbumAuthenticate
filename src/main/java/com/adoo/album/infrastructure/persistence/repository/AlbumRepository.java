package com.adoo.album.infrastructure.persistence.repository;


import com.adoo.album.model.entity.Album;
import com.adoo.album.model.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    // RF7: Filtrado por categoría, dificultad, y creador (admin)
    List<Album> findByPublicadoTrueAndCategoria(String categoria);
    List<Album> findByPublicadoTrueAndDificultad(Album.Dificultad dificultad);
    List<Album> findByPublicadoTrueAndCreador(Usuario creador);

    // Obtener álbumes publicados para la tienda/catálogo
    List<Album> findByPublicadoTrue();
}