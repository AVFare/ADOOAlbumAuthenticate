package com.adoo.album.model.entity.factory;

import com.adoo.album.model.entity.state.*;
import com.adoo.album.model.entity.state.EstadoIntercambioTipo;
import com.adoo.album.model.entity.state.IEstadoIntercambio;

public class EstadoFactory {	
	public static IEstadoIntercambio crearEstado(EstadoIntercambioTipo estado) {
        return switch (estado) {
            case CREADO -> new EstadoCreado();
            case OFERTADO -> new EstadoOfertado();
			case ACEPTADO -> new EstadoAceptado();
			case CANCELADO -> throw new UnsupportedOperationException("Unimplemented case: " + estado);
			case CERRADO -> new EstadoCerrado();
			case RECHAZADO -> throw new UnsupportedOperationException("Unimplemented case: " + estado);
		default -> throw new IllegalArgumentException("Unexpected value: " + estado);
        };
    }

    public static EstadoIntercambioTipo obtenerEnumEstado(IEstadoIntercambio estado) {
        if (estado instanceof EstadoCreado) return EstadoIntercambioTipo.CREADO;
        if (estado instanceof EstadoOfertado) return EstadoIntercambioTipo.OFERTADO;
        if (estado instanceof EstadoAceptado) return EstadoIntercambioTipo.ACEPTADO;
        if (estado instanceof EstadoCerrado) return EstadoIntercambioTipo.CERRADO;
        throw new IllegalArgumentException("Estado desconocido");
    }
}