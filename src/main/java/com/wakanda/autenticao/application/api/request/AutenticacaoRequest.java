package com.wakanda.autenticao.application.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutenticacaoRequest {
//	@NotBlank(message = "Email não pode estar em branco!")
	@Email
	private String cliente;
//	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	@NotNull
	private String senha;

	public UsernamePasswordAuthenticationToken getUserPassToken() {
		return new UsernamePasswordAuthenticationToken(cliente, senha);
	}
}
