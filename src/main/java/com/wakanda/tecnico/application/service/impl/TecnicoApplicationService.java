package com.wakanda.tecnico.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.perfis.CredencialTecnico;
import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
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
	public TecnicoIdResponse cadastraNovoTecnico(TecnicoNovoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoApplicationService - cadastraNovoTecnico");
		Tecnico tecnico = tecnicoRepository.salva(new Tecnico(tecnicoRequest));
		credencialService.criaNovaCredencial(new CredencialTecnico(tecnicoRequest));
		log.info("[finaliza] TecnicoApplicationService - cadastraNovoTecnico");
		return TecnicoIdResponse.builder().idTecnico(tecnico.getIdTecnico()).build();
	}

	@Override
	public TecnicoDetalhadoResponse buscaTecnicoPorId(UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - buscaTecnicoPorId");
		log.info("[idTecnico] {}", idTecnico);
		TecnicoDetalhadoResponse tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.map(TecnicoDetalhadoResponse::converte)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		log.info("[finaliza] TecnicoApplicationService - buscaTecnicoPorId");
		return tecnico;
	}

	@Override
	public List<TecnicoDetalhadoResponse> buscaTecnicos() {
		log.info("[inicia] TecnicoApplicationService - buscaTecnicos");
		List<Tecnico> tecnicos = tecnicoRepository.buscaTecnicos();
		log.info("[finaliza] TecnicoApplicationService - buscaTecnicos");
		return TecnicoDetalhadoResponse.converte(tecnicos);
	}

	@Override
	public void editaDadosDoTecnico(UUID idTecnico, EditaTecnicoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoApplicationService - editaDadosDoTecnico");
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		tecnico.edita(tecnicoRequest);
		tecnicoRepository.salva(tecnico);
		log.info("[finaliza] TecnicoApplicationService - editaDadosDoTecnico");
	}

	@Override
	public void deletaTecnico(UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - editaDadosDoTecnico");
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		tecnicoRepository.deletaTecnico(tecnico);
		log.info("[finaliza] TecnicoApplicationService - editaDadosDoTecnico");
	}

}
