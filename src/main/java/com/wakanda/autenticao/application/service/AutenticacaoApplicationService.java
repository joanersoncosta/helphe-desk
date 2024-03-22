package com.wakanda.autenticao.application.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.wakanda.autenticao.domain.Token;

public interface AutenticacaoApplicationService {
	Token autentica(UsernamePasswordAuthenticationToken userCredentials);

	Token reativaToken(String tokenExpirado);

}
