package com.adoo.album.model.dto;

import lombok.Data;

@Data
public class UsuarioUpdateRequestDTO {
    private String email;
    private Integer telefono;
    private String nombre;
    private String apellido;
    private String avatar_url;
}
