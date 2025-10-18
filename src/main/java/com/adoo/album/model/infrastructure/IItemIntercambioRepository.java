package com.adoo.album.model.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adoo.album.model.entity.ItemIntercambio;

public interface IItemIntercambioRepository extends JpaRepository<ItemIntercambio, Long> { }