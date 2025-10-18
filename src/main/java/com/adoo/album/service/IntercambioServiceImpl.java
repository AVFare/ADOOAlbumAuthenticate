package com.adoo.album.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.adoo.album.model.entity.*;
import com.adoo.album.model.infrastructure.*;

@Service
public class IntercambioServiceImpl implements IIntercambioService {
	private final IIntercambioRepository intercambioRepo;
	private final IItemIntercambioRepository itemRepo;
	private final IUsuarioFiguritaRepository usuarioFiguritaRepo;

	public IntercambioServiceImpl(IIntercambioRepository intercambioRepo, 
									IItemIntercambioRepository itemRepo, 
									IUsuarioFiguritaRepository usuarioFiguritaRepo) {
		this.intercambioRepo = intercambioRepo;
		this.itemRepo = itemRepo;
		this.usuarioFiguritaRepo = usuarioFiguritaRepo;
	}

	@Override
	@Transactional
	public Intercambio crear(Long usuarioCreadorId, Long usuarioReceptorId) {
		Intercambio nuevo = new Intercambio(usuarioCreadorId, usuarioReceptorId);
		return intercambioRepo.save(nuevo);
	}

	@Override
	@Transactional
	public Intercambio agregarItem(Long intercambioId, Long actorUsuarioId, Long usuarioFiguritaId) {
		Intercambio i = mustGet(intercambioId);
		ContextoIntercambio ctx = new ContextoIntercambio(i);

		// 1) Validación de estado/reglas por Contexto/Estado
		ctx.agregarItem(actorUsuarioId, usuarioFiguritaId);

		// 2) Reservar la figurita en DB (evita doble gasto)
		UsuarioFigurita uf = usuarioFiguritaRepo.findById(usuarioFiguritaId)
				.orElseThrow(() -> new IllegalArgumentException("UsuarioFigurita inexistente: " + usuarioFiguritaId));
		if (!uf.puedeOfrecer(actorUsuarioId))
			throw new IllegalStateException("No podés ofrecer esta figurita.");
		
		uf.reservarParaIntercambio();
		usuarioFiguritaRepo.save(uf);

		// 3) Agregar el ítem al intercambio
		ItemIntercambio item = new ItemIntercambio(uf, actorUsuarioId);
		i.agregarItem(item);
		itemRepo.save(item);

		return intercambioRepo.save(i);
	}

	@Override
	@Transactional
	public Intercambio enviarOferta(Long intercambioId, Long actorUsuarioId) {
		Intercambio i = mustGet(intercambioId);
		
		new ContextoIntercambio(i).enviarOferta(actorUsuarioId);
		return intercambioRepo.save(i);
	}

	@Override
	@Transactional
	public Intercambio aceptar(Long intercambioId, Long actorUsuarioId) {
		Intercambio i = mustGet(intercambioId);
		ContextoIntercambio ctx = new ContextoIntercambio(i);

		// 1) Cambiar a ACEPTADO (validado por el estado actual)
		ctx.aceptar(actorUsuarioId);

		// 2) Transferir propiedad de cada ítem de forma atómica
		i.getItems().forEach(it -> {
			UsuarioFigurita uf = it.getUsuarioFigurita();
			Long nuevoDueno = uf.getUsuarioId().equals(i.getUsuarioCreadorId()) ? i.getUsuarioReceptorId()
					: i.getUsuarioCreadorId();
			uf.transferirA(nuevoDueno);
			usuarioFiguritaRepo.save(uf);
		});

		// 3) Cerrar (ACEPTADO → CERRADO)
		ctx.cerrar();
		return intercambioRepo.save(i);
	}

	private Intercambio mustGet(Long id) {
		return intercambioRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Intercambio no existe: " + id));
	}
}