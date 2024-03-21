package com.wakanda.credencial.application.service.impl;

import org.springframework.stereotype.Service;

import com.wakanda.credencial.application.repository.CredencialRepository;
import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.CredencialCliente;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredencialApplicationService implements CredencialService {
	private final CredencialRepository credencialRepository;

	@Override
	public void criaNovaCredencial(CredencialCliente cliente) {
		log.info("[inicia] CrendencialService - criaNovaCredencial");
		var novaCredencial = new Credencial(cliente.getEmail(), cliente.getSenha(), cliente.getNome());
		credencialRepository.salva(novaCredencial);
		log.info("[finaliza] CrendencialService - criaNovaCredencial");
	}

}
