package com.wakanda.chamado.application.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/chamado")
public interface ChamadoAPI {
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ChamadoIdResponse criaNovoChamado(@RequestParam(name = "email", required = true) String token,
			@RequestBody @Valid ChamadoRequest chamadoRequest);

	@GetMapping(value = "/{idChamado}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	ChamadoDetalhadoResponse buscaChamadoPorId(@RequestParam(name = "email", required = true) String email,
			@PathVariable(value = "idChamado") UUID idChamado);

	@GetMapping(value = "/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamados(@RequestParam(name = "email", required = true) String email);

	@PatchMapping(value = "/{idChamado}/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void editaChamadoPorId(@RequestParam(name = "email", required = true) String email,
			@PathVariable(value = "idChamado") UUID idChamado, @RequestBody @Valid EditaChamadoRequest chamadoRequest);

}
