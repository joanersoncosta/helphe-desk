package com.wakanda.cliente.application.api.request;

import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteNovoRequest(
	@NotBlank(message = "Campo nome não pode está vazio.")
	String nome,
	@NotBlank(message = "Campo cpf não pode está vazio.")
	@Indexed(unique = true)
	@Size(min = 11, max = 11, message = "número do registro de contribuinte individual brasileiro (CPF) inválido")
//	@CPF
	String cpf,
	@Email
	@NotBlank(message = "Campo email não pode está vazio.")
	@Indexed(unique = true)
	String email,
	@NotBlank(message = "Campo senha não pode está vazio.")
	@Size(min = 6, max = 9)
	String senha,
	@NotNull(message = "Campo sexo não pode ser nulo.")
	String sexo	
	) {
}
