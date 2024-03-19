package com.wakanda.cliente.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.ClienteAPI;
import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
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

}
