package com.wakanda.tecnico.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.tecnico.application.api.TecnicoAPI;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
import com.wakanda.tecnico.application.service.TecnicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TecnicoRestController implements TecnicoAPI {
	private final TecnicoService tecnicoService;

	@Override
	public TecnicoIdResponse cadastraNovoTecnico(String token, TecnicoNovoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoRestController - cadastraNovoTecnico");
		TecnicoIdResponse idTecnico = tecnicoService.cadastraNovoTecnico(tecnicoRequest);
		log.info("[finaliza] TecnicoRestController - cadastraNovoTecnico");
		return idTecnico;
	}

}
