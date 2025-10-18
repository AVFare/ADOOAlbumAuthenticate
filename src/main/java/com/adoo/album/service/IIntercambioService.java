package com.adoo.album.service;

import com.adoo.album.model.entity.Intercambio;

public interface IIntercambioService {
	Intercambio crear(Long usuarioCreadorId, Long usuarioReceptorId);

	// Mientras esté en CREADO, agregamos los ítems (cada lado puede ofrecer)
	Intercambio agregarItem(Long intercambioId, Long actorUsuarioId, Long usuarioFiguritaId);

	// Pasa a OFERTADO (ya no se pueden agregar ítems)
	Intercambio enviarOferta(Long intercambioId, Long actorUsuarioId);

	// Aplica transferencias y cierra (ACEPTADO → CERRADO)
	Intercambio aceptar(Long intercambioId, Long actorUsuarioId);
}