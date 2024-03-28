package com.wakanda.cliente.application.api.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.ClienteAPI;
import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;
import com.wakanda.cliente.application.service.ClienteService;
import com.wakanda.config.security.service.TokenService;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ClienteRestController implements ClienteAPI {
	private final ClienteService clienteService;
	private final TokenService tokenService;

	@Override
	public ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest) {
		log.info("[inicia] ClienteRestController - cadastraNovoCliente");
		ClienteIdResponse clienteIdResponse = clienteService.cadastraNovoCliente(clienteRequest);
		log.info("[finaliza] ClienteRestController - cadastraNovoCliente");
		return clienteIdResponse;
	}

	@Override
	public ClienteDetalhadoResponse buscaClientePorId(String token, UUID idCliente) {
		log.info("[inicia] ClienteRestController - buscaClientePorId");
		String email = getUsuarioByToken(token);
		ClienteDetalhadoResponse clienteDetalhado = clienteService.buscaClientePorId(email, idCliente);
		log.info("[finaliza] ClienteRestController - buscaClientePorId");
		return clienteDetalhado;
	}

	@Override
	public List<ClienteListResponse> buscaTodosOsClientes(String token) {
		log.info("[inicia] ClienteRestController - buscaTodosOsClientes");
		String email = getUsuarioByToken(token);
		List<ClienteListResponse> clienteListResponse = clienteService.buscaTodosOsClientes(email);		
		log.info("[finaliza] ClienteRestController - buscaTodosOsClientes");		
		return clienteListResponse;
	}

	@Override
	public void editaDadosDoCliente(String token, UUID idCliente, EditaClienteRequest clienteRequest) {
		log.info("[inicia] ClienteRestController - editaDadosDoCliente");
		String email = getUsuarioByToken(token);
		clienteService.editaDadosDoCliente(email, idCliente, clienteRequest);		
		log.info("[finaliza] ClienteRestController - editaDadosDoCliente");		
	}

	@Override
	public void deletaCliente(String token, UUID idCliente) {
		log.info("[inicia] ClienteRestController - deletaCliente");
		String email = getUsuarioByToken(token);
		clienteService.deletaCliente(email, idCliente);		
		log.info("[finaliza] ClienteRestController - deletaCliente");		
	}
	
	private String getUsuarioByToken(String token) {
		log.info("[inicia] ChamadoRestController - getUsuarioByToken");
		log.debug("[token] {}", token);
		String emailUsuario = tokenService.getUsuarioByBearerToken(token)
				.orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[finaliza] ChamadoRestController - getUsuarioByToken");
		return emailUsuario;
	}
}
