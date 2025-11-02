package com.adoo.album.service.api;

import com.adoo.album.model.dto.UsuarioUpdateRequestDTO;
import com.adoo.album.model.entity.Usuario;

public interface IUsuarioService {
    Usuario findUser(String username, String password);
    Usuario findUser(String username);
    Usuario registerUser(Usuario usuario);

    Usuario actualizarUsuario(Long id, UsuarioUpdateRequestDTO dto);
    Usuario obtenerUsuarioPorId(Long id);
    Usuario findByEmail(String email);
}

