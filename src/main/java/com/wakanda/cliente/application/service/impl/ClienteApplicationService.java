package com.wakanda.cliente.application.service.impl;

import org.springframework.stereotype.Service;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.service.ClienteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClienteApplicationService implements ClienteService {

	@Override
	public ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - cadastraNovoCliente");
		log.info("[finaliza] ClienteApplicationService - cadastraNovoCliente");
		return null;
	}

}
