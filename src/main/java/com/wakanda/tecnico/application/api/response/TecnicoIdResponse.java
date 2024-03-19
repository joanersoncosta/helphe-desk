package com.wakanda.tecnico.application.api.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TecnicoIdResponse {
	private UUID idTecnico;
}
