package com.wakanda.tecnico.application.service.impl;

import org.springframework.stereotype.Service;

import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
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

	@Override
	public TecnicoIdResponse cadastraNovoTecnico(TecnicoNovoRequest tecnicoRequest) {
		log.info("[inicia] TecnicoApplicationService - cadastraNovoTecnico");
		Tecnico tecnico = tecnicoRepository.salva(new Tecnico(tecnicoRequest));
		log.info("[finaliza] TecnicoApplicationService - cadastraNovoTecnico");
		return TecnicoIdResponse.builder().idTecnico(tecnico.getIdTecnico()).build();
	}

}
