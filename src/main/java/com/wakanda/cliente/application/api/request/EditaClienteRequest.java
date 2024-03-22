package com.wakanda.cliente.application.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record EditaClienteRequest(
		@NotBlank(message = "Campo nome não pode está vazio.")
		String nome,
		@NotNull(message = "Campo sexo não pode está vazio.")
		String sexo) {
}