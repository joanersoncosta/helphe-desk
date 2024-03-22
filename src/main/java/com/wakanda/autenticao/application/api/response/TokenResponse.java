package com.wakanda.autenticao.application.api.response;

import com.wakanda.autenticao.domain.Token;

import lombok.Value;

@Value
public class TokenResponse {
	private String token;
	private String tipo;

	public TokenResponse(Token token) {
		this.token = token.getToken();
		this.tipo = token.getTipo();
	}
}