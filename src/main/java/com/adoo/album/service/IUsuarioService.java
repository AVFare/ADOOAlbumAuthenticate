package com.adoo.album.service;

import com.adoo.album.model.entity.Usuario;

public interface IUsuarioService {
	public Usuario findUser(String username, String password);
	public Usuario findUser(String username);
}
