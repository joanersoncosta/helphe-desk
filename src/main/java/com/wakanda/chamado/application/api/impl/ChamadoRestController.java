package com.wakanda.chamado.application.api.impl;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.ChamadoAPI;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
import com.wakanda.chamado.application.service.ChamadoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChamadoRestController implements ChamadoAPI {
	private final ChamadoService chamadoService;

	@Override
	public ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest) {
		log.info("[inicia] ChamadoRestController - criaNovoChamado");
		ChamadoIdResponse idChamado = chamadoService.criaNovoChamado(email, chamadoRequest);
		log.info("[finaliza] ChamadoRestController - criaNovoChamado");
		return idChamado;
	}

	@Override
	public ChamadoDetalhadoResponse buscaChamadoPorId(String email, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - buscaChamadoPorId");
		ChamadoDetalhadoResponse chamado = chamadoService.buscaChamadoPorId(idChamado);
		log.info("[finaliza] ChamadoRestController - buscaChamadoPorId");
		return chamado;
	}

}
