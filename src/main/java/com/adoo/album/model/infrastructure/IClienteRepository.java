package com.adoo.album.model.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adoo.album.model.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
