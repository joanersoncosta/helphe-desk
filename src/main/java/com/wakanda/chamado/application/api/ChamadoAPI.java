package com.wakanda.chamado.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/chamado")
public interface ChamadoAPI {
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ChamadoIdResponse criaNovoChamado(@RequestParam(name = "email", required = true) String token,
			@RequestBody @Valid ChamadoRequest chamadoRequest);

}
