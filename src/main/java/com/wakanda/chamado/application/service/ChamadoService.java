package com.wakanda.chamado.application.service;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

public interface ChamadoService {

	ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest);

}
