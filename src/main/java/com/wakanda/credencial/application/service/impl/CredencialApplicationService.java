package com.wakanda.credencial.application.service.impl;

import org.springframework.stereotype.Service;

import com.wakanda.credencial.application.repository.CredencialRepository;
import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.perfis.CredencialAdmin;
import com.wakanda.credencial.domain.perfis.CredencialCliente;
import com.wakanda.credencial.domain.perfis.CredencialTecnico;

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
		var novaCredencial = new Credencial(cliente.getEmail(), cliente.getSenha(), cliente.getPerfil());
		credencialRepository.salva(novaCredencial);
		log.info("[finaliza] CrendencialService - criaNovaCredencial");
	}

	@Override
	public void criaNovaCredencial(CredencialTecnico tecnico) {
		log.info("[inicia] CrendencialService - criaNovaCredencial");
		var novaCredencial = new Credencial(tecnico.getEmail(), tecnico.getSenha(), tecnico.getPerfil());
		credencialRepository.salva(novaCredencial);
		log.info("[finaliza] CrendencialService - criaNovaCredencial");
	}
	
	@Override
	public void criaNovaCredencial(CredencialAdmin admin) {
		log.info("[inicia] CrendencialService - criaNovaCredencial");
		var novaCredencial = new Credencial(admin.getEmail(), admin.getSenha(), admin.getPerfil());
		credencialRepository.salva(novaCredencial);
		log.info("[finaliza] CrendencialService - criaNovaCredencial");
	}

	@Override
	public Credencial buscaCredencialPorUsuario(String usuario) {
		log.info("[inicia] CredencialSpringDataJpaService - buscaCredencialPorUsuario");
		Credencial credencial = credencialRepository.buscaCredencialPorUsuario(usuario);
		log.info("[finaliza] CredencialSpringDataJpaService - buscaCredencialPorUsuario");
		return credencial;
	}

	@Override
	public void deletaCredencial(String email) {
		log.info("[inicia] CredencialSpringDataJpaService - deletaCredencial");
		Credencial credencial = credencialRepository.buscaCredencialPorUsuario(email);
		credencialRepository.deletaCredencial(credencial);
		log.info("[finaliza] CredencialSpringDataJpaService - deletaCredencial");
	}

}
