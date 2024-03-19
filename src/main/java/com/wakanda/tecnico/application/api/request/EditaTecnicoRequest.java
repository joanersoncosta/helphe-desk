package com.wakanda.tecnico.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditaTecnicoRequest(@NotBlank(message = "Campo nome não pode está vazio.") String nome,
		@NotNull(message = "Campo sexo não pode está vazio.") String sexo) {
}