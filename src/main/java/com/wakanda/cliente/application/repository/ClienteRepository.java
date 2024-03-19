package com.wakanda.cliente.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.wakanda.cliente.domain.Cliente;

public interface ClienteRepository {

	Cliente salva(Cliente cliente);

	Cliente detalhaClientePorEmail(String email);

	Optional<Cliente> detalhaClientePorId(UUID idCliente);

}
