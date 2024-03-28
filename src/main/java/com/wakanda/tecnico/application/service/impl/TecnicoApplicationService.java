package com.wakanda.tecnico.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.perfis.CredencialTecnico;
import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
import com.wakanda.tecnico.application.api.response.TecnicoListDetalhadoResponse;
import com.wakanda.tecnico.application.repository.TecnicoRepository;
import com.wakanda.tecnico.application.service.TecnicoService;
import com.wakanda.tecnico.domain.Tecnico;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TecnicoApplicationService implements TecnicoService {
	private final TecnicoRepository tecnicoRepository;
	private final CredencialService credencialService;

	@Override
	public TecnicoIdResponse cadastraNovoTecnico(String email, TecnicoNovoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoApplicationService - cadastraNovoTecnico");
		Credencial credenciaUsuario = buscaCredencialPorUsuario(email);
		log.info("[credenciaUsuario] {}", credenciaUsuario);
		credenciaUsuario.validaCredencialAdmin();
		Tecnico tecnico = tecnicoRepository.salva(new Tecnico(tecnicoRequest, credenciaUsuario.getUsername()));
		credencialService.criaNovaCredencial(new CredencialTecnico(tecnicoRequest));
		log.info("[finaliza] TecnicoApplicationService - cadastraNovoTecnico");
		return TecnicoIdResponse.builder().idTecnico(tecnico.getIdTecnico()).build();
	}

	@Override
	public TecnicoDetalhadoResponse buscaTecnicoPorId(String email, UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - buscaTecnicoPorId");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		tecnico.pertenceAoTecnico(emailTecnico);
		log.info("[finaliza] TecnicoApplicationService - buscaTecnicoPorId");
		return TecnicoDetalhadoResponse.converte(tecnico);
	}

	@Override
	public List<TecnicoListDetalhadoResponse> buscaTecnicos(String email) {
		log.info("[inicia] TecnicoApplicationService - buscaTecnicos");
		buscaCredencialPorUsuario(email);
		List<Tecnico> tecnicos = tecnicoRepository.buscaTecnicos();
		log.info("[finaliza] TecnicoApplicationService - buscaTecnicos");
		return TecnicoListDetalhadoResponse.converte(tecnicos);
	}

	@Override
	public void editaDadosDoTecnico(String email, UUID idTecnico, EditaTecnicoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoApplicationService - editaDadosDoTecnico");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		tecnico.pertenceAoTecnico(emailTecnico);
		tecnico.edita(tecnicoRequest);
		tecnicoRepository.salva(tecnico);
		log.info("[finaliza] TecnicoApplicationService - editaDadosDoTecnico");
	}

	@Override
	public void deletaTecnico(String email, UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - editaDadosDoTecnico");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		tecnico.pertenceAoTecnico(emailTecnico);
		tecnicoRepository.deletaTecnico(tecnico);
		log.info("[finaliza] TecnicoApplicationService - editaDadosDoTecnico");
	}

	private Credencial buscaCredencialPorUsuario(String email) {
		log.info("[inicia] TecnicoRestController - buscaCredencialPorUsuario");
		Credencial credencialUsuario = credencialService.buscaCredencialPorUsuario(email);
		log.info("[finaliza] TecnicoRestController - buscaCredencialPorUsuario");
		return credencialUsuario;
	}

}
