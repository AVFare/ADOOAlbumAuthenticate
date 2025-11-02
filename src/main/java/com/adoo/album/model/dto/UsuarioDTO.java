package com.adoo.album.model.dto;

import java.sql.Date;

import com.adoo.album.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class UsuarioDTO {
	private String username;
	private String password;
	private Role role;
	private String email;
	private int telefono;
	private String nombre;
	private String apellido;
	private String avatar_url;
	private Date created_at;
}
