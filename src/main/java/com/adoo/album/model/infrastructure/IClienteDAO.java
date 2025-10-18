package com.adoo.album.model.infrastructure;

import java.util.List;

import com.adoo.album.model.entity.Cliente;

public interface IClienteDAO {
	public List<Cliente> findAll();

	public Cliente findById(int id);

	public void save(Cliente cliente);

	public void deleteById(int id);
}
