package com.wakanda.tecnico.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;

public interface TecnicoService {

	TecnicoIdResponse cadastraNovoTecnico(TecnicoNovoRequest tecnicoRequest);

	TecnicoDetalhadoResponse buscaTecnicoPorId(UUID idTecnico);

	List<TecnicoDetalhadoResponse> buscaTecnicos();

}
