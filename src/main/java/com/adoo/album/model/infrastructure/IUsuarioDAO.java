package com.adoo.album.model.infrastructure;

import com.adoo.album.model.entity.Usuario;

public interface IUsuarioDAO {
	public Usuario findUser(String username, String password);
	public Usuario findUser(String username);
	public Usuario save(Usuario usuario);
}
