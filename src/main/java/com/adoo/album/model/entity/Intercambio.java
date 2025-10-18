package com.adoo.album.model.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.adoo.album.model.entity.state.EstadoIntercambioTipo;

@Entity
@Table(name = "intercambios")
public class Intercambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long usuarioCreadorId;
	private Long usuarioReceptorId;

	@Enumerated(EnumType.STRING)
	private EstadoIntercambioTipo estado;

	private Instant creadoEn;
	private Instant actualizadoEn;
	private Instant cerradoEn;

	@OneToMany(mappedBy = "intercambio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ItemIntercambio> items = new ArrayList<>();

	protected Intercambio() {
	}

	public Intercambio(Long usuarioCreadorId, Long usuarioReceptorId) {
		this.usuarioCreadorId = usuarioCreadorId;
		this.usuarioReceptorId = usuarioReceptorId;
		this.estado = EstadoIntercambioTipo.CREADO;
		this.creadoEn = Instant.now();
		this.actualizadoEn = this.creadoEn;
	}

	public void agregarItem(ItemIntercambio item) {
		item.setIntercambio(this);
		items.add(item);
		tocar();
	}

	public void tocar() {
		this.actualizadoEn = Instant.now();
	}

	public void marcarCerrado() {
		this.cerradoEn = Instant.now();
	}

	// getters/setters esenciales
	public Long getId() {
		return id;
	}

	public Long getUsuarioCreadorId() {
		return usuarioCreadorId;
	}

	public Long getUsuarioReceptorId() {
		return usuarioReceptorId;
	}

	public EstadoIntercambioTipo getEstado() {
		return estado;
	}

	public void setEstado(EstadoIntercambioTipo estado) {
		this.estado = estado;
		tocar();
	}

	public List<ItemIntercambio> getItems() {
		return items;
	}
}