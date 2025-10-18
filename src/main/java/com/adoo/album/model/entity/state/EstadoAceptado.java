package com.adoo.album.model.entity.state;

import com.adoo.album.model.entity.ContextoIntercambio;

public class EstadoAceptado implements IEstadoIntercambio {

	@Override
	public void agregarItem(ContextoIntercambio ctx, Long actorUsuarioId, Long usuarioFiguritaId) {
		throw new IllegalStateException("Aceptado: no se pueden agregar más ítems.");
	}

	@Override
	public void enviarOferta(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está ACEPTADO.");
	}

	@Override
	public void aceptar(ContextoIntercambio ctx, Long actorUsuarioId) {
		
	}

	@Override
	public void rechazar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está ACEPTADO.");
	}

	@Override
	public void cancelar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está ACEPTADO.");
	}

	@Override
	public void cerrar(ContextoIntercambio ctx) {
		ctx.setEstadoActual(new EstadoCerrado());
	}
}