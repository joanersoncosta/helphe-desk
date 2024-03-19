package com.wakanda.cliente.infra.impl;

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
		}catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Cliente já cadastrado.");
		}
		log.info("[finish] ClienteInfraRepository - salva");
		return cliente;
	}

}
