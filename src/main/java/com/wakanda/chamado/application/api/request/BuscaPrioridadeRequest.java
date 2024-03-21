package com.wakanda.chamado.application.api.request;

import jakarta.validation.constraints.NotBlank;

public record BuscaPrioridadeRequest(@NotBlank(message = "Campo prioridade não pode está vazio.") String prioridade) {
}
