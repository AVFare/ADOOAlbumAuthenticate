package com.adoo.album.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
		name = "Autenticación",
		description = "Endpoints para el inicio de sesión y registro de usuarios"
)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IAuthService service;
	@Operation(
			summary = "Iniciar sesión",
			description = "Permite a un usuario autenticarse enviando sus credenciales. Devuelve un token JWT y los datos básicos del usuario.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Autenticación exitosa",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))
					),
					@ApiResponse(
							responseCode = "401",
							description = "Credenciales inválidas",
							content = @Content(mediaType = "application/json")
					)
			}
	)
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO credentials) {
		return ResponseEntity.ok(service.login(credentials));
	}

	@Operation(
			summary = "Registrar nuevo usuario",
			description = "Crea una nueva cuenta de usuario en el sistema. Devuelve la información básica del usuario recién creado.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Usuario registrado correctamente",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponseDTO.class))
					),
					@ApiResponse(
							responseCode = "400",
							description = "Datos inválidos o usuario existente",
							content = @Content(mediaType = "application/json")
					)
			}
	)
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO nuevoUsuario) {
		return ResponseEntity.ok(service.register(nuevoUsuario));
	}

}
