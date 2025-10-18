package com.adoo.album.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items_intercambio")
public class ItemIntercambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_figurita_id")
	private UsuarioFigurita usuarioFigurita;

	private Long ofrecidoPorUsuarioId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intercambio_id")
	private Intercambio intercambio;

	protected ItemIntercambio() {
	}

	public ItemIntercambio(UsuarioFigurita usuarioFigurita, Long ofrecidoPorUsuarioId) {
		this.usuarioFigurita = usuarioFigurita;
		this.ofrecidoPorUsuarioId = ofrecidoPorUsuarioId;
	}

	public void setIntercambio(Intercambio intercambio) {
		this.intercambio = intercambio;
	}

	public UsuarioFigurita getUsuarioFigurita() {
		return usuarioFigurita;
	}

	public Long getOfrecidoPorUsuarioId() {
		return ofrecidoPorUsuarioId;
	}
}