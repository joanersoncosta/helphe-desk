package com.wakanda.chamado.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

@RestController
@RequestMapping("/v1/chamado")
public interface ChamadoAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ChamadoIdResponse criaNovoChamado(@RequestHeader("Authorization") String token,
			@RequestBody @Valid ChamadoRequest chamadoRequest);

	@GetMapping(value = "/{idChamado}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	ChamadoDetalhadoResponse buscaChamadoPorId(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

	@GetMapping(value = "/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamados(@RequestHeader("Authorization") String token);

	@GetMapping(value = "/restrito/prioridade/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamadosPorPrioridade(
			@RequestHeader("Authorization") String token,
			@RequestBody @Valid BuscaPrioridadeRequest prioridadeRequest);

	@GetMapping(value = "/restrito/status/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamadosPorStatus(@RequestHeader("Authorization") String token,
			@RequestBody @Valid BuscaStatusRequest statusRequest);

	@GetMapping(value = "/cliente/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamadosDoCliente(@RequestHeader("Authorization") String token);

	@GetMapping(value = "/tecnico/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<ChamadoDetalhadoResponse> buscaChamadosDoTecnico(@RequestHeader("Authorization") String token);

	@PatchMapping(value = "/{idChamado}/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void editaChamadoPorId(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado, @RequestBody @Valid EditaChamadoRequest chamadoRequest);

	@PatchMapping(value = "/{idChamado}/restrito/prioridade/media/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void mudaPrioridadeParaMedia(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

	@PatchMapping(value = "/{idChamado}/restrito/prioridade/alta/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void mudaPrioridadeParaAlta(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

	@PatchMapping(value = "/{idChamado}/restrito/status/andamento/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void mudaStatusParaAndamento(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

	@PatchMapping(value = "/{idChamado}/restrito/status/encerrado/edita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void mudaStatusParaEncerrado(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

	@DeleteMapping(value = "/{idChamado}/deleta")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void deletaChamadoPorId(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idChamado") UUID idChamado);

}
