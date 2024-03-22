package com.wakanda.tecnico.application.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

public record TecnicoNovoRequest(
		@NotBlank(message = "Campo nome não pode está vazio.")
		String nome,
		@NotNull(message = "Campo cpf não pode ser nulo.")
		@Indexed(unique = true)
		@Size(min = 11, max = 11, message = "número do registro de contribuinte individual brasileiro (CPF) inválido")
//		@CPF
		String cpf,
		@Email
		@NotNull(message = "Campo email não pode ser nulo.")
		@Indexed(unique = true)
		String email,
		@NotBlank(message = "Campo senha não pode está vazio.")
		@Size(min = 6, max = 9)
		String senha,
		@NotNull(message = "Digite novamente o sexo.")
		String sexo	
		) {}
