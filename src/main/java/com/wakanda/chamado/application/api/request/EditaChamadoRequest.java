package com.wakanda.chamado.application.api.request;

import jakarta.validation.constraints.NotBlank;

public record EditaChamadoRequest(
		@NotBlank(message = "Campo titulo não pode está vazio.") String titulo,
		@NotBlank(message = "Campo observações não pode está vazio.") String observacoes) {
}