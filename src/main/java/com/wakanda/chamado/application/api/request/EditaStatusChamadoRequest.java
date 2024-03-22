package com.wakanda.chamado.application.api.request;

import javax.validation.constraints.NotBlank;

public record EditaStatusChamadoRequest(@NotBlank(message = "Campo status não pode está vazio.") String status) {

}
