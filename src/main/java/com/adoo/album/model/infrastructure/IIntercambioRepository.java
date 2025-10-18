package com.adoo.album.model.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adoo.album.model.entity.Intercambio;

public interface IIntercambioRepository extends JpaRepository<Intercambio, Long> { }