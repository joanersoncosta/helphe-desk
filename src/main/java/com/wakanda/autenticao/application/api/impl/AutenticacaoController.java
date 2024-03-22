package com.wakanda.autenticao.application.api.impl;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.autenticao.application.api.AutenticacaoAPI;
import com.wakanda.autenticao.application.api.request.AutenticacaoRequest;
import com.wakanda.autenticao.application.api.response.TokenResponse;
import com.wakanda.autenticao.application.service.AutenticacaoService;
import com.wakanda.config.security.domain.ValidaConteudoAuthorizationHeader;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AutenticacaoController implements AutenticacaoAPI {
	private final AutenticacaoService autenticacaoService;

	@Override
	@ResponseStatus(code = HttpStatus.OK)
	public TokenResponse autentica(AutenticacaoRequest autenticacaoRequest) {
		log.info("[inicio] Iniciando Metodo autenciacao em AutenticacaoController");
		var token = autenticacaoService.autentica(autenticacaoRequest.getUserPassToken());
		log.info("[finaliza] Retornando Token para o cliente");
		return new TokenResponse(token);
	}

	@Override
	@ResponseStatus(code = HttpStatus.OK)
	public TokenResponse reativaAutenticacao(String tokenExpirado) throws AuthenticationException {
		log.info("[inicio] Iniciando Metodo revalidaAutenciacao em AutenticacaoController");
		String tokenExpiradoValido = validaTokenExpirado(Optional.of(tokenExpirado));
		var token = autenticacaoService.reativaToken(tokenExpiradoValido);
		log.info("[finaliza] Retornando Token atualizado para o cliente");
		return new TokenResponse(token);
	}

	private String validaTokenExpirado(Optional<String> tokenExpirado) {
		String tokenExp = tokenExpirado.filter(new ValidaConteudoAuthorizationHeader())
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Token Invalido!"));
		return tokenExp.substring(7, tokenExp.length());
	}

}
