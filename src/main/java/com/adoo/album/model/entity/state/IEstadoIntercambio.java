package com.adoo.album.model.entity.state;

import com.adoo.album.model.entity.ContextoIntercambio;
import com.adoo.album.model.entity.Intercambio;

public interface IEstadoIntercambio {
	void agregarItem(ContextoIntercambio contexto, Long actorUsuarioId, Long usuarioFiguritaId);
    void enviarOferta(ContextoIntercambio contexto, Long actorUsuarioId);
    void aceptar(ContextoIntercambio contexto, Long actorUsuarioId);
    void rechazar(ContextoIntercambio contexto, Long actorUsuarioId);
    void cancelar(ContextoIntercambio contexto, Long actorUsuarioId);
    void cerrar(ContextoIntercambio contexto);
}