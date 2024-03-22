package com.wakanda.admin.application.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AdminRequest(@NotBlank(message = "Campo email não pode está vazio.") String email,
		@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres") String senha) {
}
