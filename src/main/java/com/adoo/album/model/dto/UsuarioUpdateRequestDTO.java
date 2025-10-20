package com.adoo.album.model.dto;

import lombok.Data;

@Data
public class UsuarioUpdateRequestDTO {
    private String email;
    private int telefono;
    private String nombre;
    private String apellido;
    private String avatar_url;
}
