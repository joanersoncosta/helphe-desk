package com.wakanda.tecnico.application.service;

import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;

public interface TecnicoService {

	TecnicoIdResponse cadastraNovoTecnico(TecnicoNovoRequest tecnicoRequest);

}
