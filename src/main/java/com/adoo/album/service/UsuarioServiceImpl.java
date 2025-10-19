package com.adoo.album.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adoo.album.model.infrastructure.IUsuarioDAO;
import com.adoo.album.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuariosDAO;

	@Override
	public Usuario findUser(String username, String password) {
		Usuario usuario = usuariosDAO.findUser(username, password);
		return usuario;
	}

	@Override
	public Usuario findUser(String username) {
		Usuario usuario = usuariosDAO.findUser(username);
		return usuario;
	}

	

}
