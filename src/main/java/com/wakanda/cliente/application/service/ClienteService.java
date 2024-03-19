package com.wakanda.cliente.application.service;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;

public interface ClienteService {

	ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest);

}
