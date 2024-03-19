package com.wakanda.cliente.infra.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.wakanda.cliente.application.repository.ClienteRepository;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.cliente.infra.ClienteSpringDBMongoRepository;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {
	private final ClienteSpringDBMongoRepository clienteSpringDBMongoRepository;

	@Override
	public Cliente salva(Cliente cliente) {
		log.info("[start] ClienteInfraRepository - salva");
		try {
			clienteSpringDBMongoRepository.save(cliente);
		} catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Cliente já cadastrado.");
		}
		log.info("[finish] ClienteInfraRepository - salva");
		return cliente;
	}

	@Override
	public Cliente detalhaClientePorEmail(String email) {
		log.info("[start] ClienteInfraRepository - detalhaClientePorEmail");
		Cliente cliente = clienteSpringDBMongoRepository.findByEmail(email)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado para esse email."));
		log.info("[inicia] ClienteInfraRepository - detalhaClientePorEmail");
		return cliente;
	}

	@Override
	public Optional<Cliente> detalhaClientePorId(UUID idCliente) {
		log.info("[start] ClienteInfraRepository - detalhaClientePorId");
		Optional<Cliente> cliente = clienteSpringDBMongoRepository.findById(idCliente);
		log.info("[finish] ClienteInfraRepository - detalhaClientePorId");
		return cliente;
	}

	@Override
	public List<Cliente> buscaClientes() {
		log.info("[start] ClienteInfraRepository - buscaClientes");
		List<Cliente> clientes = clienteSpringDBMongoRepository.findAll();
		log.info("[inicia] ClienteInfraRepository - buscaClientes");
		return clientes;
	}

	@Override
	public void deletaCliente(Cliente cliente) {
		log.info("[start] ClienteInfraRepository - buscaClientes");
		clienteSpringDBMongoRepository.delete(cliente);
		log.info("[inicia] ClienteInfraRepository - buscaClientes");
	}

}
