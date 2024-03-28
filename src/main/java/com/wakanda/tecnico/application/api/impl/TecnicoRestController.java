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
import com.wakanda.tecnico.application.api.response.TecnicoListDetalhadoResponse;
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
		String email = getUsuarioByToken(token);
		TecnicoIdResponse tecnicoIdResponse = tecnicoService.cadastraNovoTecnico(email, tecnicoRequest);
		log.info("[finaliza] TecnicoRestController - cadastraNovoTecnico");
		return tecnicoIdResponse;
	}

	@Override
	public TecnicoDetalhadoResponse buscaTecnicoPorId(String token, UUID idTecnico) {
		log.info("[inicia] TecnicoRestController - buscaTecnicoPorId");
		String email = getUsuarioByToken(token);
		TecnicoDetalhadoResponse tecnicoDetalhadoResponse = tecnicoService.buscaTecnicoPorId(email, idTecnico);
		log.info("[finaliza] TecnicoRestController - buscaTecnicoPorId");
		return tecnicoDetalhadoResponse;
	}

	@Override
	public List<TecnicoListDetalhadoResponse> buscaTecnicos(String token) {
		log.info("[inicia] TecnicoRestController - buscaTecnicos");
		String email = getUsuarioByToken(token);
		List<TecnicoListDetalhadoResponse> tecnicoListDetalhadoResponse = tecnicoService.buscaTecnicos(email);
		log.info("[finaliza] TecnicoRestController - buscaTecnicos");
		return tecnicoListDetalhadoResponse;
	}

	@Override
	public void editaDadosDoTecnico(String token, UUID idTecnico, EditaTecnicoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoRestController - editaDadosDoTecnico");
		String email = getUsuarioByToken(token);
		tecnicoService.editaDadosDoTecnico(email, idTecnico, tecnicoRequest);
		log.info("[finaliza] TecnicoRestController - editaDadosDoTecnico");
	}

	@Override
	public void deletaTecnico(String token, UUID idTecnico) {
		log.info("[inicia] TecnicoRestController - deletaTecnico");
		String email = getUsuarioByToken(token);
		tecnicoService.deletaTecnico(email, idTecnico);
		log.info("[finaliza] TecnicoRestController - deletaTecnico");
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
