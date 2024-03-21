package com.wakanda.chamado.application.api.request;

import jakarta.validation.constraints.NotBlank;

public record EditaStatusChamadoRequest(@NotBlank(message = "Campo status não pode está vazio.") String status) {

}
