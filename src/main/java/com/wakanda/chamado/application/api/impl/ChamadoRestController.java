package com.wakanda.chamado.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.ChamadoAPI;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
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

}
