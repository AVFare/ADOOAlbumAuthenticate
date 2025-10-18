package com.adoo.album.model.entity.state;

import com.adoo.album.model.entity.ContextoIntercambio;
import com.adoo.album.model.entity.Intercambio;

public class EstadoCreado implements IEstadoIntercambio {

	@Override
	public void agregarItem(ContextoIntercambio ctx, Long actorUsuarioId, Long usuarioFiguritaId) {
		Intercambio i = ctx.getIntercambio();
		if (!actorUsuarioId.equals(i.getUsuarioCreadorId()) && !actorUsuarioId.equals(i.getUsuarioReceptorId())) {
			throw new SecurityException("El actor no pertenece al intercambio.");
		}
	}

	@Override
	public void enviarOferta(ContextoIntercambio ctx, Long actorUsuarioId) {
		Intercambio i = ctx.getIntercambio();
		if (!actorUsuarioId.equals(i.getUsuarioCreadorId()) && !actorUsuarioId.equals(i.getUsuarioReceptorId())) {
			throw new SecurityException("El actor no pertenece al intercambio.");
		}
		ctx.setEstadoActual(new EstadoOfertado());
	}

	@Override
	public void aceptar(ContextoIntercambio ctx, Long actorUsuarioId) {
		throw new IllegalStateException("No se puede aceptar un intercambio en estado CREADO.");
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
		throw new IllegalStateException("No se puede cerrar un intercambio en estado CREADO.");
	}
}