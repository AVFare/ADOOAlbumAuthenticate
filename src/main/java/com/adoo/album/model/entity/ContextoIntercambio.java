package com.adoo.album.model.entity;

import com.adoo.album.model.entity.factory.EstadoFactory;
import com.adoo.album.model.entity.state.IEstadoIntercambio;

public class ContextoIntercambio {

	private Intercambio intercambio;
	private IEstadoIntercambio estadoActual;

	public ContextoIntercambio(Intercambio intercambio) {
		this.intercambio = intercambio;
		this.estadoActual = EstadoFactory.crearEstado(intercambio.getEstado());
	}
	
	public void agregarItem(Long actorUsuarioId, Long usuarioFiguritaId) {
        estadoActual.agregarItem(this, actorUsuarioId, usuarioFiguritaId);
    }

    public void enviarOferta(Long actorUsuarioId) {
        estadoActual.enviarOferta(this, actorUsuarioId);
    }

    public void aceptar(Long actorUsuarioId) {
        estadoActual.aceptar(this, actorUsuarioId);
    }

    public void rechazar(Long actorUsuarioId) {
        estadoActual.rechazar(this, actorUsuarioId);
    }

    public void cancelar(Long actorUsuarioId) {
        estadoActual.cancelar(this, actorUsuarioId);
    }

    public void cerrar() {
        estadoActual.cerrar(this);
    }

    public Intercambio getIntercambio() {
        return intercambio;
    }

    public void setEstadoActual(IEstadoIntercambio nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.intercambio.setEstado(EstadoFactory.obtenerEnumEstado(nuevoEstado));
    }
}
