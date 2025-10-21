package com.adoo.album.service.impl;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adoo.album.model.dto.LoginRequestDTO;
import com.adoo.album.model.dto.LoginResponseDTO;
import com.adoo.album.model.dto.RegisterRequestDTO;
import com.adoo.album.model.dto.RegisterResponseDTO;
import com.adoo.album.model.entity.Usuario;
import com.adoo.album.model.exceptions.UserAlreadyExistsException;
import com.adoo.album.service.IAuthService;
import com.adoo.album.service.IUsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImp implements IAuthService {
    
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private SecretKey secretKey;
    private final int EXPIRATION_TIME_IN_MIN = 60;
    
    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        if (usuarioService.findUser(request.getUsername()) != null) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        Usuario nuevoUsuario = new Usuario(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getRole()
        );

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
        Usuario usuario = usuarioService.findUser(credentials.getUsername(), credentials.getPassword());

        if (usuario == null) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Generar token
        String token = Jwts.builder()
                .setSubject(credentials.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Devolver DTO de respuesta
        return LoginResponseDTO.builder()
                .accessToken(token)
                .role(usuario.getRole())
                .build();
    }




}
