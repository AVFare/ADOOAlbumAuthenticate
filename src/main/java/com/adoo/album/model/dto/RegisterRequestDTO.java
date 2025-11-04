package com.adoo.album.model.dto;

import java.sql.Date;

import com.adoo.album.model.enums.Role;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
	private int telefono;
	private String nombre;
	private String apellido;
	private String avatar_url;
	private Date created_at;
}
