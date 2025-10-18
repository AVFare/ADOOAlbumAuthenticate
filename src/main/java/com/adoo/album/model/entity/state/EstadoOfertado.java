package com.adoo.album.model.entity.state;

import com.adoo.album.model.entity.ContextoIntercambio;

public class EstadoOfertado implements IEstadoIntercambio {

	@Override
	public void agregarItem(ContextoIntercambio ctx, Long actorUsuarioId, Long usuarioFiguritaId) {
		throw new IllegalStateException("Oferta enviada: no se pueden agregar más ítems.");
	}

	@Override
	public void enviarOferta(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("Ya está OFERTADO.");
	}

	@Override
	public void aceptar(ContextoIntercambio ctx, Long actorUsuarioId) {
		ctx.setEstadoActual(new EstadoAceptado());
	}

	@Override
	public void rechazar(ContextoIntercambio ctx, Long actorUsuarioId) {
		// ctx.setEstadoActual(new EstadoRechazado());
	}

	@Override
	public void cancelar(ContextoIntercambio ctx, Long actorUsuarioId) {
		// ctx.setEstadoActual(new EstadoCancelado());
	}

	@Override
	public void cerrar(ContextoIntercambio ctx) {
		throw new IllegalStateException("No se puede cerrar un intercambio en estado OFERTADO.");
	}
}