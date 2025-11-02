package com.adoo.album.service.impl;

//import java.sql.Date;
import java.util.Date;

import javax.crypto.SecretKey;

import com.adoo.album.service.api.IAuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adoo.album.model.dto.LoginRequestDTO;
import com.adoo.album.model.dto.LoginResponseDTO;
import com.adoo.album.model.dto.RegisterRequestDTO;
import com.adoo.album.model.dto.RegisterResponseDTO;
import com.adoo.album.model.entity.Usuario;
import com.adoo.album.exceptions.EmailAlreadyExistsException;
import com.adoo.album.exceptions.InvalidCredentialsException;
import com.adoo.album.exceptions.UserAlreadyExistsException;
import com.adoo.album.exceptions.UserNotFoundException;
import com.adoo.album.service.api.IUsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class AuthService implements IAuthService {
    
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private SecretKey secretKey;
    private final int EXPIRATION_TIME_IN_MIN = 60;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        if (usuarioService.findUser(request.getUsername()) != null) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        if (usuarioService.findByEmail(request.getEmail()) != null) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());
        nuevoUsuario.setPassword(request.getPassword());
        nuevoUsuario.setRole(request.getRole());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setTelefono(request.getTelefono());
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setApellido(request.getApellido());
        nuevoUsuario.setAvatar_url(request.getAvatar_url());
        nuevoUsuario.setCreated_at(new java.sql.Date(System.currentTimeMillis()));

        usuarioService.registerUser(nuevoUsuario);

        String token = Jwts.builder()
                .setSubject(request.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return RegisterResponseDTO.builder()
                .accessToken(token)
                .role(request.getRole())
                .build();
    }


    @Override
    public LoginResponseDTO login(LoginRequestDTO credentials) {
        Usuario usuario = usuarioService.findUser(credentials.getUsername());

        if (usuario == null) {
            throw new UserNotFoundException(credentials.getUsername());
        }

        if (!passwordEncoder.matches(credentials.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("userId", usuario.getId())
                .claim("role", usuario.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return LoginResponseDTO.builder()
                .accessToken(token)
                .role(usuario.getRole())
                .build();
    }
}
