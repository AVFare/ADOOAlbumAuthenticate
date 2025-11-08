package com.adoo.album.controller;

import com.adoo.album.config.JwtAuthFilter;
import com.adoo.album.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import io.swagger.v3.oas.annotations.tags.Tag;

import com.adoo.album.model.dto.UsuarioProfileResponseDTO;
import com.adoo.album.model.dto.UsuarioUpdateRequestDTO;
import com.adoo.album.model.entity.Usuario;
import com.adoo.album.service.api.IUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para consulta y actualización de perfiles de usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

@Operation(
        summary = "Actualizar el perfil del usuario autenticado",
        description = "Permite que un usuario autenticado actualice únicamente su propio perfil. " +
                "El id del path debe coincidir con el id presente en el token JWT.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Perfil actualizado correctamente"),
                @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content),
                @ApiResponse(responseCode = "403", description = "No autorizado para modificar este perfil", content = @Content),
                @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
        }
)
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


    @Operation(
            summary = "Obtener perfil de un usuario por id",
            description = "Devuelve el perfil de un usuario por su id. " ,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Perfil obtenido correctamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
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
