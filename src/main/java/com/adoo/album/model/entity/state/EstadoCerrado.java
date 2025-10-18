package com.adoo.album.model.entity.state;

import com.adoo.album.model.entity.ContextoIntercambio;

public class EstadoCerrado implements IEstadoIntercambio {

	@Override
	public void agregarItem(ContextoIntercambio ctx, Long actorUsuarioId, Long usuarioFiguritaId) {
		throw new IllegalStateException("Cerrada: no se pueden agregar más ítems.");
	}

	@Override
	public void enviarOferta(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está CERRADO.");
	}

	@Override
	public void aceptar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está CERRADO.");
	}

	@Override
	public void rechazar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está CERRADO.");
	}

	@Override
	public void cancelar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está CERRADO.");
	}

	@Override
	public void cerrar(ContextoIntercambio ctx) {
		
	}
}