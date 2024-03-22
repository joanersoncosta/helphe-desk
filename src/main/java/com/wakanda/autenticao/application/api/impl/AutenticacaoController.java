package com.wakanda.autenticao.application.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.autenticao.application.api.AutenticacaoAPI;
import com.wakanda.autenticao.application.api.TokenResponse;
import com.wakanda.autenticao.application.api.request.AutenticacaoRequest;
import com.wakanda.autenticao.application.service.AutenticacaoService;

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

}
