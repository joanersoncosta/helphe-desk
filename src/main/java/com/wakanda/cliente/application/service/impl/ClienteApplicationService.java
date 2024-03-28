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
import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.perfis.CredencialCliente;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClienteApplicationService implements ClienteService {
	private final ClienteRepository clienteRepository;
	private final CredencialService credencialService;
	
	@Override
	public ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - cadastraNovoCliente");
		Cliente cliente = clienteRepository.salva(new Cliente(clienteRequest));
		credencialService.criaNovaCredencial(new CredencialCliente(clienteRequest));
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
		cliente.pertenceAoCliente(emailCliente.getIdCliente());
		log.info("[finaliza] ClienteApplicationService - buscaClientePorId");
		return ClienteDetalhadoResponse.converteClienteParaResponse(cliente);
	}

	@Override
	public List<ClienteListResponse> buscaTodosOsClientes(String email) {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		Credencial credenciaUsuario = buscaCredencialPorUsuario(email);
		log.info("[credenciaUsuario] {}", credenciaUsuario);
		credenciaUsuario.validaCredencialUsuario();
		List<Cliente> clientes = clienteRepository.buscaClientes();		
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
		return ClienteListResponse.converte(clientes);
	}

	@Override
	public void editaDadosDoCliente(String email, UUID idCliente, EditaClienteRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		Cliente emailCliente = clienteRepository.detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = clienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
		cliente.pertenceAoCliente(emailCliente.getIdCliente());
		cliente.editaDadosDoCliente(clienteRequest);
		clienteRepository.salva(cliente);
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
	}

	@Override
	public void deletaCliente(String email, UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - buscaTodosOsClientes");
		Cliente emailCliente = clienteRepository.detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = clienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
		cliente.pertenceAoCliente(emailCliente.getIdCliente());
		credencialService.deletaCredencial(emailCliente.getEmail());
		clienteRepository.deletaCliente(cliente);
		log.info("[finaliza] ClienteApplicationService - buscaTodosOsClientes");
	}
	
	private Credencial buscaCredencialPorUsuario(String email) {
		log.info("[inicia] ClienteApplicationService - buscaCredencialPorUsuario");
		Credencial credencialUsuario = credencialService.buscaCredencialPorUsuario(email);
		log.info("[finaliza] ClienteApplicationService - buscaCredencialPorUsuario");
		return credencialUsuario;
	}

}
