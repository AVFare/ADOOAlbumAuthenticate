package com.adoo.album.model.infrastructure;

import java.util.Optional;

import com.adoo.album.model.entity.Usuario;

public interface IUsuarioDAO {
    Usuario findUser(String username, String password);
    Usuario findUser(String username);
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Usuario findByEmail(String email);
}

