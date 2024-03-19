package com.wakanda.cliente.application.service;

import java.util.UUID;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;

public interface ClienteService {

	ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest);

	ClienteDetalhadoResponse buscaClientePorId(String email, UUID idCliente);

}
