package com.wakanda.cliente.application.api.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClienteIdResponse {
	private UUID idCliente;
}
