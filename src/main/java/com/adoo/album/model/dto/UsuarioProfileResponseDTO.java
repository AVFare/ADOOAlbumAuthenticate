package com.adoo.album.model.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioProfileResponseDTO {
    private Long id;
    private String username;
    private String email;
    private int telefono;
    private String nombre;
    private String apellido;
    private String avatar_url;
    private Date created_at;
}

