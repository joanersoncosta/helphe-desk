package com.wakanda.chamado.application.api.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;

public record ChamadoRequest(
		@Indexed @NotNull(message = "Campo idTecnico não pode ser nulo.") UUID idTecnico,
		@NotBlank(message = "Campo prioridade não pode está vazio.") String prioridade,
		@NotBlank(message = "Campo titulo não pode está vazio.") String titulo,
		@NotBlank(message = "Campo observacoes não pode está vazio.") String observacoes) {
}
