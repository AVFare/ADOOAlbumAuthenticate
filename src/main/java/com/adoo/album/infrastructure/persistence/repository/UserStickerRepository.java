package com.adoo.album.infrastructure.persistence.repository;

import com.adoo.album.model.entity.Sticker;
import com.adoo.album.model.entity.UserSticker;
import com.adoo.album.model.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {

    // RF4: Ver colección completa del usuario por álbum
    List<UserSticker> findByUserAndSticker_Album_Id(Usuario user, Long albumId);

    // RF4: Contar cuántas copias tiene el usuario de una figurita específica
    long countByUserAndSticker(Usuario user, Sticker sticker);

    // RF4: Ver duplicadas (que no estén en trade)
    List<UserSticker> findByUserAndEstado(Usuario user, UserSticker.Estado estado);
}