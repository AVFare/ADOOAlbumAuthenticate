package com.adoo.album.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adoo.album.model.infrastructure.IUsuarioDAO;
import com.adoo.album.service.IUsuarioService;
import com.adoo.album.model.dto.UsuarioUpdateRequestDTO;
import com.adoo.album.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario findUser(String username, String password) {
        return usuarioDAO.findUser(username, password);
    }

    @Override
    public Usuario findUser(String username) {
        return usuarioDAO.findUser(username);
    }

    @Override
    public Usuario registerUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioDAO.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
    }

    @Override
    public Usuario actualizarUsuario(Long id, UsuarioUpdateRequestDTO dto) {
        Usuario usuario = usuarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setAvatar_url(dto.getAvatar_url());

        return usuarioDAO.save(usuario);
    }
}

