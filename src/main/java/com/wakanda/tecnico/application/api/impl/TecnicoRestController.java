package com.wakanda.tecnico.application.api.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.config.security.service.TokenService;
import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.api.TecnicoAPI;
import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
import com.wakanda.tecnico.application.service.TecnicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TecnicoRestController implements TecnicoAPI {
	private final TecnicoService tecnicoService;
	private final TokenService tokenService;

	@Override
	public TecnicoIdResponse cadastraNovoTecnico(String token, TecnicoNovoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoRestController - cadastraNovoTecnico");
		getUsuarioByToken(token);
		TecnicoIdResponse idTecnico = tecnicoService.cadastraNovoTecnico(tecnicoRequest);
		log.info("[finaliza] TecnicoRestController - cadastraNovoTecnico");
		return idTecnico;
	}

	@Override
	public TecnicoDetalhadoResponse buscaTecnicoPorId(String token, UUID idTecnico) {
		log.info("[inicia] TecnicoRestController - buscaTecnicoPorId");
		getUsuarioByToken(token);
		TecnicoDetalhadoResponse tecnico = tecnicoService.buscaTecnicoPorId(idTecnico);
		log.info("[finaliza] TecnicoRestController - buscaTecnicoPorId");
		return tecnico;
	}

	@Override
	public List<TecnicoDetalhadoResponse> buscaTecnicos(String token) {
		log.info("[inicia] TecnicoRestController - buscaTecnicos");
		getUsuarioByToken(token);
		List<TecnicoDetalhadoResponse> tecnicos = tecnicoService.buscaTecnicos();
		log.info("[finaliza] TecnicoRestController - buscaTecnicos");
		return tecnicos;
	}

	@Override
	public void editaDadosDoTecnico(String token, UUID idTecnico, EditaTecnicoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoRestController - editaDadosDoTecnico");
		getUsuarioByToken(token);
		tecnicoService.editaDadosDoTecnico(idTecnico, tecnicoRequest);
		log.info("[finaliza] TecnicoRestController - editaDadosDoTecnico");
	}

	@Override
	public void deletaTecnico(String token, UUID idTecnico) {
		log.info("[inicia] TecnicoRestController - deletaTecnico");
		getUsuarioByToken(token);
		tecnicoService.deletaTecnico(idTecnico);
		log.info("[finaliza] TecnicoRestController - deletaTecnico");
	}

	private String getUsuarioByToken(String token) {
		log.debug("[token] {}", token);
		String usuario = tokenService.getUsuarioByBearerToken(token).orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[usuario] {}", usuario);
		return usuario;
	}
}
