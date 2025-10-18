package com.adoo.album.controller;

import org.springframework.web.bind.annotation.*;

import com.adoo.album.model.entity.*;
import com.adoo.album.service.IIntercambioService;

@RestController
@RequestMapping("/api/intercambios")
public class IntercambioController {
	private final IIntercambioService servicio;

	public IntercambioController(IIntercambioService servicio) {
		this.servicio = servicio;
	}

	// Crear intercambio
	@PostMapping
	public Intercambio crear(@RequestParam Long creadorId, @RequestParam Long receptorId) {
		return servicio.crear(creadorId, receptorId);
	}

	// Agregar ítem ofrecido (antes de ofertar)
	@PostMapping("/{id}/items")
	public Intercambio agregarItem(@PathVariable Long id, @RequestParam Long actorUsuarioId,
			@RequestParam Long usuarioFiguritaId) {
		return servicio.agregarItem(id, actorUsuarioId, usuarioFiguritaId);
	}

	// Enviar oferta (bloquea edición de ítems)
	@PostMapping("/{id}/oferta")
	public Intercambio enviarOferta(@PathVariable Long id, @RequestParam Long actorUsuarioId) {
		return servicio.enviarOferta(id, actorUsuarioId);
	}

	// Aceptar → transfiere figuritas y cierra
	@PostMapping("/{id}/aceptar")
	public Intercambio aceptar(@PathVariable Long id, @RequestParam Long actorUsuarioId) {
		return servicio.aceptar(id, actorUsuarioId);
	}
}