package com.wakanda.cliente.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;
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

	@Override
	public List<ClienteListResponse> buscaTodosOsClientes() {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		List<Cliente> clientes = clienteRepository.buscaClientes();		
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
		return ClienteListResponse.converte(clientes);
	}

	@Override
	public void editaDadosDoCliente(String email, EditaClienteRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		Cliente cliente = clienteRepository.detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		cliente. editaDadosDoCliente(clienteRequest);
		clienteRepository.salva(cliente);
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
	}

	@Override
	public void deletaCliente(String email) {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		Cliente cliente = clienteRepository.detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		clienteRepository.deletaCliente(cliente);
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
	}

}
