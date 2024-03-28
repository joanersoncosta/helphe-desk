package com.wakanda.tecnico.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
import com.wakanda.tecnico.application.api.response.TecnicoListDetalhadoResponse;

public interface TecnicoService {

	TecnicoIdResponse cadastraNovoTecnico(String email, TecnicoNovoRequest tecnicoRequest);

	TecnicoDetalhadoResponse buscaTecnicoPorId(String email, UUID idTecnico);

	List<TecnicoListDetalhadoResponse> buscaTecnicos(String email);

	void editaDadosDoTecnico(String email,UUID idTecnico, EditaTecnicoRequest tecnicoRequest);

	void deletaTecnico(String email,UUID idTecnico);

}
