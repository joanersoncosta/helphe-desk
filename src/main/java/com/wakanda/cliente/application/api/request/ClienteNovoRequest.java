package com.wakanda.cliente.application.api.request;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteNovoRequest(
	@NotBlank(message = "Campo nome não pode está vazio.")
	String nome,
	@NotNull(message = "Campo cpf não pode ser nulo.")
	@Indexed(unique = true)
	@CPF
	String cpf,
	@Email
	@NotNull(message = "Campo email não pode ser nulo.")
	@Indexed(unique = true)
	String email,
	@NotNull(message = "Digite novamente o sexo.")
	String sexo	
	) {
}
