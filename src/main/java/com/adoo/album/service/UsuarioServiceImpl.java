package com.adoo.album.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adoo.album.model.infrastructure.IUsuarioDAO;
import com.adoo.album.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Override
	public Usuario findUser(String username, String password) {
		Usuario usuario = usuarioDAO.findUser(username, password);
		return usuario;
	}

	@Override
	public Usuario findUser(String username) {
		Usuario usuario = usuarioDAO.findUser(username);
		return usuario;
	}

	@Override
	public Usuario registerUser(Usuario usuario) {
		return usuarioDAO.save(usuario);
	}	

}
