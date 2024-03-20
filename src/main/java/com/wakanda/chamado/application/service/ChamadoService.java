package com.wakanda.chamado.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

public interface ChamadoService {

	ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest);

	ChamadoDetalhadoResponse buscaChamadoPorId(UUID idChamado);

	List<ChamadoDetalhadoResponse> buscaChamados();

}
