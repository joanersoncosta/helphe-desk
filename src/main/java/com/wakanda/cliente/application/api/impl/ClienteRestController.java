package com.wakanda.cliente.application.api.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.ClienteAPI;
import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;
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

	@Override
	public List<ClienteListResponse> buscaTodosOsClientes(String email) {
		log.info("[inicia] ClienteRestController - buscaTodosOsClientes");
		List<ClienteListResponse> clientes = clienteService.buscaTodosOsClientes();		
		log.info("[finaliza] ClienteRestController - buscaTodosOsClientes");		
		return clientes;
	}

	@Override
	public void editaDadosDoCliente(String email, EditaClienteRequest clienteRequest) {
		log.info("[inicia] ClienteRestController - editaDadosDoCliente");
		clienteService.editaDadosDoCliente(email, clienteRequest);		
		log.info("[finaliza] ClienteRestController - editaDadosDoCliente");		
	}

	@Override
	public void deletaCliente(String email) {
		log.info("[inicia] ClienteRestController - deletaCliente");
		clienteService.deletaCliente(email);		
		log.info("[finaliza] ClienteRestController - deletaCliente");		
	}

}
