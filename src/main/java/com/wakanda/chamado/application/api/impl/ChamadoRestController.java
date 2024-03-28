package com.wakanda.chamado.application.api.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.ChamadoAPI;
import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
import com.wakanda.chamado.application.api.response.ChamadoListDetalhadoResponse;
import com.wakanda.chamado.application.service.ChamadoService;
import com.wakanda.config.security.service.TokenService;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChamadoRestController implements ChamadoAPI {
	private final ChamadoService chamadoService;
	private final TokenService tokenService;

	@Override
	public ChamadoIdResponse criaNovoChamado(String token, ChamadoRequest chamadoRequest) {
		log.info("[inicia] ChamadoRestController - criaNovoChamado");
		String email = getUsuarioByToken(token);
		ChamadoIdResponse chamadoIdResponse = chamadoService.criaNovoChamado(email, chamadoRequest);
		log.info("[finaliza] ChamadoRestController - criaNovoChamado");
		return chamadoIdResponse;
	}

	@Override
	public ChamadoDetalhadoResponse buscaChamadoPorId(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - buscaChamadoPorId");
		String email = getUsuarioByToken(token);
		ChamadoDetalhadoResponse chamadoDetalhadoResponse = chamadoService.buscaChamadoPorId(email, idChamado);
		log.info("[finaliza] ChamadoRestController - buscaChamadoPorId");
		return chamadoDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamados(String token) {
		log.info("[inicia] ChamadoRestController - buscaChamados");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService.buscaChamados(email);
		log.info("[finaliza] ChamadoRestController - buscaChamados");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosPorPrioridade(String token,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosPorPrioridade");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService
				.buscaChamadosPorPrioridade(email, prioridadeRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosPorPrioridade");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorPrioridade(String token, UUID idTecnico,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoTecnicoPorPrioridade");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService
				.buscaChamadosDoTecnicoPorPrioridade(email, idTecnico, prioridadeRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoTecnicoPorPrioridade");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorPrioridade(String token, UUID idCliente,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoClientePorPrioridade");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService
				.buscaChamadosDoClientePorPrioridade(email, idCliente, prioridadeRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoClientePorPrioridade");
		return chamadoListDetalhadoResponse;

	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosPorStatus(String token, BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosPorStatus");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService.buscaChamadosPorStatus(email,
				statusRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosPorStatus");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorStatus(String token, UUID idTecnico,
			BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoTecnicoPorStatus");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService
				.buscaChamadosDoTecnicoPorStatus(email, idTecnico, statusRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoTecnicoPorStatus");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorStatus(String token, UUID idCliente,
			BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoClientePorStatus");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService
				.buscaChamadosDoClientePorStatus(email, idCliente, statusRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoClientePorStatus");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoCliente(String token, UUID idCliente) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoCliente");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService.buscaChamadosDoCliente(email,
				idCliente);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoCliente");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnico(String token, UUID idTecnico) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoTecnico");
		String email = getUsuarioByToken(token);
		List<ChamadoListDetalhadoResponse> chamadoListDetalhadoResponse = chamadoService.buscaChamadosDoTecnico(email,
				idTecnico);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoTecnico");
		return chamadoListDetalhadoResponse;
	}

	@Override
	public void editaChamadoPorId(String token, UUID idChamado, EditaChamadoRequest chamadoRequest) {
		log.info("[inicia] ChamadoRestController - editaChamadoPorId");
		String email = getUsuarioByToken(token);
		chamadoService.editaChamadoPorId(email, idChamado, chamadoRequest);
		log.info("[finaliza] ChamadoRestController - editaChamadoPorId");
	}

	@Override
	public void mudaPrioridadeParaMedia(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaPrioridadeParaMedia");
		String email = getUsuarioByToken(token);
		chamadoService.mudaPrioridadeParaMedia(email, idChamado);
		log.info("[finaliza] ChamadoRestController - mudaPrioridadeParaMedia");
	}

	@Override
	public void mudaPrioridadeParaAlta(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaPrioridadeParaAlta");
		String email = getUsuarioByToken(token);
		chamadoService.mudaPrioridadeParaAlta(email, idChamado);
		log.info("[finaliza] ChamadoRestController - mudaPrioridadeParaAlta");
	}

	@Override
	public void mudaStatusParaAndamento(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaStatusParaAndamento");
		String email = getUsuarioByToken(token);
		chamadoService.mudaStatusParaAndamento(email, idChamado);
		log.info("[finaliza] ChamadoRestController - mudaStatusParaAndamento");
	}

	@Override
	public void mudaStatusParaEncerrado(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaStatusParaEncerrado");
		String email = getUsuarioByToken(token);
		chamadoService.mudaStatusParaEncerrado(email, idChamado);
		log.info("[finaliza] ChamadoRestController - mudaStatusParaEncerrado");
	}

	@Override
	public void deletaChamadoPorId(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - deletaChamadoPorId");
		String email = getUsuarioByToken(token);
		chamadoService.deletaChamadoPorId(email, idChamado);
		log.info("[finaliza] ChamadoRestController - deletaChamadoPorId");
	}

	private String getUsuarioByToken(String token) {
		log.info("[inicia] ChamadoRestController - getUsuarioByToken");
		log.debug("[token] {}", token);
		String emailUsuario = tokenService.getUsuarioByBearerToken(token)
				.orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[finaliza] ChamadoRestController - getUsuarioByToken");
		return emailUsuario;
	}

}
