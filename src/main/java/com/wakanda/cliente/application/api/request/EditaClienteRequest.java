package com.wakanda.cliente.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditaClienteRequest(
		@NotBlank(message = "Campo nome não pode está vazio.")
		String nome,
		@NotNull(message = "Campo sexo não pode está vazio.")
		String sexo) {
}