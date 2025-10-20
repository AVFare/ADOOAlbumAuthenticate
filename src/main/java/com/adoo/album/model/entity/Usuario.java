package com.adoo.album.model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String email;
	private int telefono;
	private String nombre;
	private String apellido;
	private String avatar_url;
	private Date created_at;


	public Usuario() {
		super();
	}

	public Usuario(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
}
