package com.wakanda.chamado.application.api.request;

import javax.validation.constraints.NotBlank;

public record BuscaStatusRequest(@NotBlank(message = "Campo status não pode está vazio.") String status) {
}
