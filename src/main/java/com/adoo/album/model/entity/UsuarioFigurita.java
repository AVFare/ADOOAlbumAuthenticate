package com.adoo.album.model.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "usuarios_figuritas", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id", "figurita_id" }))
public class UsuarioFigurita {

	public enum EstadoPropiedad {
		EN_COLECCION, RESERVADA, TRANSFERIDA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "usuario_id")
	private Long usuarioId;

	@Column(name = "figurita_id")
	private Long figuritaId;

	@Enumerated(EnumType.STRING)
	private EstadoPropiedad estado = EstadoPropiedad.EN_COLECCION;

	private Instant actualizadoEn = Instant.now();

	@Version
	private Long version;

	public boolean puedeOfrecer(Long actorUsuarioId) {
		return this.usuarioId.equals(actorUsuarioId) && estado == EstadoPropiedad.EN_COLECCION;
	}

	public void reservarParaIntercambio() {
		if (estado != EstadoPropiedad.EN_COLECCION)
			throw new IllegalStateException("Figurita no disponible para ofrecer.");
		estado = EstadoPropiedad.RESERVADA;
		actualizadoEn = Instant.now();
	}

	public void liberarReserva() {
		if (estado == EstadoPropiedad.RESERVADA) {
			estado = EstadoPropiedad.EN_COLECCION;
			actualizadoEn = Instant.now();
		}
	}

	public void transferirA(Long nuevoUsuarioId) {
		if (estado != EstadoPropiedad.RESERVADA)
			throw new IllegalStateException("Debe estar RESERVADA para transferir.");
		this.usuarioId = nuevoUsuarioId;
		this.estado = EstadoPropiedad.TRANSFERIDA;
		actualizadoEn = Instant.now();
	}

	public Long getUsuarioId() {
		return usuarioId;
	}
}