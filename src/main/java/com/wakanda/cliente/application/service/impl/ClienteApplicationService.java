package com.wakanda.cliente.application.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.repository.ClienteRepository;
import com.wakanda.cliente.application.service.ClienteService;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClienteApplicationService implements ClienteService {
	private final ClienteRepository clienteRepository;

	@Override
	public ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - cadastraNovoCliente");
		Cliente cliente = clienteRepository.salva(new Cliente(clienteRequest));
		log.info("[finaliza] ClienteApplicationService - cadastraNovoCliente");
		return ClienteIdResponse.builder().idCliente(cliente.getIdCliente()).build();
	}

	@Override
	public ClienteDetalhadoResponse buscaClientePorId(String email, UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - buscaClientePorId");
		Cliente emailCliente = clienteRepository.detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = clienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
		cliente.pertenceAoCliente(emailCliente);
		log.info("[finaliza] ClienteApplicationService - buscaClientePorId");
		return ClienteDetalhadoResponse.converteClienteParaResponse(cliente);

	}

}
