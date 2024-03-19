package com.wakanda.cliente.application.api.impl;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.ClienteAPI;
import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.service.ClienteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ClienteRestController implements ClienteAPI {
	private final ClienteService clienteService;

	@Override
	public ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest) {
		log.info("[inicia] ClienteRestController - cadastraNovoCliente");
		ClienteIdResponse idCliente = clienteService.cadastraNovoCliente(clienteRequest);
		log.info("[finaliza] ClienteRestController - cadastraNovoCliente");
		return idCliente;
	}

	@Override
	public ClienteDetalhadoResponse buscaClientePorId(String email, UUID idCliente) {
		log.info("[inicia] ClienteRestController - buscaClientePorId");
		ClienteDetalhadoResponse clienteDetalhado = clienteService.buscaClientePorId(email, idCliente);
		log.info("[finaliza] ClienteRestController - buscaClientePorId");
		return clienteDetalhado;
	}

}
