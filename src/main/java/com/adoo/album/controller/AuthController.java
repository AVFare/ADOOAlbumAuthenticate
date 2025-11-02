package com.adoo.album.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adoo.album.model.dto.LoginRequestDTO;
import com.adoo.album.model.dto.LoginResponseDTO;
import com.adoo.album.model.dto.RegisterRequestDTO;
import com.adoo.album.model.dto.RegisterResponseDTO;
import com.adoo.album.service.api.IAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IAuthService service;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO credentials) {
		return ResponseEntity.ok(service.login(credentials));
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO nuevoUsuario) {
		return ResponseEntity.ok(service.register(nuevoUsuario));
	}

}
