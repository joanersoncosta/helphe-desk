package com.wakanda.chamado.application.api.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.chamado.application.api.ChamadoAPI;
import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
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
		ChamadoIdResponse idChamado = chamadoService.criaNovoChamado(email, chamadoRequest);
		log.info("[finaliza] ChamadoRestController - criaNovoChamado");
		return idChamado;
	}

	@Override
	public ChamadoDetalhadoResponse buscaChamadoPorId(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - buscaChamadoPorId");
		getUsuarioByToken(token);
		ChamadoDetalhadoResponse chamado = chamadoService.buscaChamadoPorId(idChamado);
		log.info("[finaliza] ChamadoRestController - buscaChamadoPorId");
		return chamado;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamados(String token) {
		log.info("[inicia] ChamadoRestController - buscaChamados");
		getUsuarioByToken(token);
		List<ChamadoDetalhadoResponse> chamados = chamadoService.buscaChamados();
		log.info("[finaliza] ChamadoRestController - buscaChamados");
		return chamados;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosPorPrioridade(String token,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosPorPrioridade");
		getUsuarioByToken(token);
		List<ChamadoDetalhadoResponse> chamados = chamadoService.buscaChamadosPorPrioridade(prioridadeRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosPorPrioridade");
		return chamados;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosPorStatus(String token, BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoRestController - buscaChamadosPorStatus");
		getUsuarioByToken(token);
		List<ChamadoDetalhadoResponse> chamados = chamadoService.buscaChamadosPorStatus(statusRequest);
		log.info("[finaliza] ChamadoRestController - buscaChamadosPorStatus");
		return chamados;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosDoCliente(String token) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoCliente");
		String email = getUsuarioByToken(token);
		List<ChamadoDetalhadoResponse> chamados = chamadoService.buscaChamadosDoCliente(email);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoCliente");
		return chamados;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosDoTecnico(String token) {
		log.info("[inicia] ChamadoRestController - buscaChamadosDoTecnico");
		String email = getUsuarioByToken(token);
		List<ChamadoDetalhadoResponse> chamados = chamadoService.buscaChamadosDoTecnico(email);
		log.info("[finaliza] ChamadoRestController - buscaChamadosDoTecnico");
		return chamados;
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
		getUsuarioByToken(token);
		chamadoService.mudaPrioridadeParaMedia(idChamado);
		log.info("[finaliza] ChamadoRestController - mudaPrioridadeParaMedia");
	}

	@Override
	public void mudaPrioridadeParaAlta(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaPrioridadeParaAlta");
		getUsuarioByToken(token);
		chamadoService.mudaPrioridadeParaAlta(idChamado);
		log.info("[finaliza] ChamadoRestController - mudaPrioridadeParaAlta");
	}

	@Override
	public void mudaStatusParaAndamento(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaStatusParaAndamento");
		getUsuarioByToken(token);
		chamadoService.mudaStatusParaAndamento(idChamado);
		log.info("[finaliza] ChamadoRestController - mudaStatusParaAndamento");
	}

	@Override
	public void mudaStatusParaEncerrado(String token, UUID idChamado) {
		log.info("[inicia] ChamadoRestController - mudaStatusParaEncerrado");
		getUsuarioByToken(token);
		chamadoService.mudaStatusParaEncerrado(idChamado);
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
		log.debug("[token] {}", token);
		String usuario = tokenService.getUsuarioByBearerToken(token)
				.orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[usuario] {}", usuario);
		return usuario;
	}

}
