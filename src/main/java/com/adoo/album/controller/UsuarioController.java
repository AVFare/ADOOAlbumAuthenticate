package com.adoo.album.controller;

import com.adoo.album.config.JwtAuthFilter;
import com.adoo.album.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adoo.album.model.dto.UsuarioProfileResponseDTO;
import com.adoo.album.model.dto.UsuarioUpdateRequestDTO;
import com.adoo.album.model.entity.Usuario;
import com.adoo.album.service.api.IUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

@PutMapping("/update/{id}")
    public ResponseEntity<UsuarioProfileResponseDTO> actualizarUsuario(
        @PathVariable Long id,
        @RequestBody UsuarioUpdateRequestDTO request) {

    // Obtener usuario logueado desde el token
    var auth = SecurityContextHolder.getContext().getAuthentication();
    var principal = (AuthUser) auth.getPrincipal();
    if (!principal.id().equals(id)) throw new AccessDeniedException("No autorizado");

    Usuario loggedUser = usuarioService.findUser(principal.username());

    // Validar que el usuario solo pueda modificar su propio perfil
    if (!loggedUser.getId().equals(id)) {
        throw new RuntimeException("No autorizado para modificar este perfil");
    }

    // Actualizar
    Usuario actualizado = usuarioService.actualizarUsuario(id, request);

    UsuarioProfileResponseDTO response = UsuarioProfileResponseDTO.builder()
            .id(actualizado.getId())
            .username(actualizado.getUsername())
            .email(actualizado.getEmail())
            .telefono(actualizado.getTelefono())
            .nombre(actualizado.getNombre())
            .apellido(actualizado.getApellido())
            .avatar_url(actualizado.getAvatar_url())
            .created_at(actualizado.getCreated_at())
            .build();

    return ResponseEntity.ok(response);
}


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioProfileResponseDTO> obtenerPerfil(@PathVariable Long id) {
        // GET sigue siendo público
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);

        UsuarioProfileResponseDTO response = UsuarioProfileResponseDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .avatar_url(usuario.getAvatar_url())
                .created_at(usuario.getCreated_at())
                .build();

        return ResponseEntity.ok(response);
    }
}
