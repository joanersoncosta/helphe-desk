package com.wakanda.chamado.application.api.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChamadoIdResponse {
	private UUID idChamado;
}
