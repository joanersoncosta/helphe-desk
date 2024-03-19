package com.wakanda.cliente.application.api.response;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class ClienteIdResponse {
	private UUID idCliente;
}
