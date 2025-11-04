package com.adoo.album.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adoo.album.infrastructure.persistence.custom.IUsuarioDAO;
import com.adoo.album.service.api.IUsuarioService;
import com.adoo.album.model.dto.UsuarioUpdateRequestDTO;
import com.adoo.album.model.entity.Usuario;
import com.adoo.album.exceptions.EmailAlreadyExistsException;
import com.adoo.album.exceptions.UserNotFoundException;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

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
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
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
                .orElseThrow(() -> new UserNotFoundException(id));

        if (dto.getEmail() != null && !dto.getEmail().equals(usuario.getEmail())) {
            Usuario other = usuarioDAO.findByEmail(dto.getEmail());
            if (other != null && !other.getId().equals(id)) {
                throw new EmailAlreadyExistsException(dto.getEmail());
            }
            usuario.setEmail(dto.getEmail());
        }
        
        if (dto.getTelefono() != null) usuario.setTelefono(dto.getTelefono());
        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getApellido() != null) usuario.setApellido(dto.getApellido());
        if (dto.getAvatar_url() != null) usuario.setAvatar_url(dto.getAvatar_url());

        return usuarioDAO.save(usuario);
    }

}

