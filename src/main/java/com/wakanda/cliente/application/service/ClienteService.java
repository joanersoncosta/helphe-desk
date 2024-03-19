package com.wakanda.cliente.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;

public interface ClienteService {

	ClienteIdResponse cadastraNovoCliente(ClienteNovoRequest clienteRequest);

	ClienteDetalhadoResponse buscaClientePorId(String email, UUID idCliente);

	List<ClienteListResponse> buscaTodosOsClientes();

}
