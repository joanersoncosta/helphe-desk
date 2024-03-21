package com.wakanda.chamado.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
import com.wakanda.chamado.application.repository.ChamadoRepository;
import com.wakanda.chamado.application.service.ChamadoService;
import com.wakanda.chamado.domain.Chamado;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;
import com.wakanda.cliente.application.repository.ClienteRepository;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.repository.TecnicoRepository;
import com.wakanda.tecnico.domain.Tecnico;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChamadoApllicationService implements ChamadoService {
	private final ClienteRepository ClienteRepository;
	private final ChamadoRepository chamadoRepository;
	private final TecnicoRepository tecnicoRepository;

	@Override
	public ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest) {
		log.info("[inicia] TecnicoApplicationService - cadastraNovoTecnico");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		Tecnico tecnico = detalhaTecnicoPorId(chamadoRequest.idTecnico());
		log.info("[tecnico] {}", tecnico);
		Chamado Chamado = chamadoRepository.salva(new Chamado(cliente.getIdCliente(), chamadoRequest));
		log.info("[finaliza] TecnicoApplicationService - cadastraNovoTecnico");
		return ChamadoIdResponse.builder().idChamado(Chamado.getIdChamado()).build();
	}

	private Tecnico detalhaTecnicoPorId(UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - detalhaTecnicoPorId");
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		log.info("[finaliza] TecnicoApplicationService - detalhaTecnicoPorId");
		return tecnico;
	}

	private Cliente detalhaClientePorEmail(String email) {
		log.info("[inicia] TecnicoApplicationService - detalhaClientePorEmail");
		Cliente cliente = ClienteRepository.detalhaClientePorEmail(email);
		log.info("[finaliza] TecnicoApplicationService - detalhaClientePorEmail");
		return cliente;
	}

	@Override
	public ChamadoDetalhadoResponse buscaChamadoPorId(UUID idChamado) {
		log.info("[inicia] TecnicoApplicationService - buscaChamadoPorId");
		log.info("[idChamado] {}", idChamado);
		Chamado chamado = chamadoRepository.buscaChamadoPorId(idChamado)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Chamado não encontrado para este ID."));
		log.info("[finaliza] TecnicoApplicationService - buscaChamadoPorId");
		return ChamadoDetalhadoResponse.converte(chamado);
	}

	public Chamado detalhaChamado(UUID idChamado) {
		log.info("[inicia] TecnicoApplicationService - detalhaChamado");
		Chamado chamado = chamadoRepository.buscaChamadoPorId(idChamado)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Chamado não encontrado para este ID."));
		log.info("[finaliza] TecnicoApplicationService - detalhaChamado");
		return chamado;
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamados() {
		log.info("[inicia] TecnicoApplicationService - buscaChamados");
		List<Chamado> Chamados = chamadoRepository.buscaChamados();
		log.info("[finaliza] TecnicoApplicationService - buscaChamados");
		return ChamadoDetalhadoResponse.converte(Chamados);
	}

	@Override
	public void editaChamadoPorId(String email, UUID idChamado, EditaChamadoRequest chamadoRequest) {
		log.info("[inicia] TecnicoApplicationService - editaChamadoPorId");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		Chamado chamado = detalhaChamado(idChamado);
		chamado.pertenceAoCliente(cliente);
		chamado.edita(chamadoRequest);
		chamadoRepository.salva(chamado);
		log.info("[finaliza] TecnicoApplicationService - editaChamadoPorId");
	}

	@Override
	public void deletaChamadoPorId(String email, UUID idChamado) {
		log.info("[inicia] TecnicoApplicationService - deletaChamadoPorId");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		Chamado chamado = detalhaChamado(idChamado);
		chamado.pertenceAoCliente(cliente);
		chamadoRepository.deletaChamado(chamado);
		log.info("[finaliza] TecnicoApplicationService - deletaChamadoPorId");
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosPorPrioridade(BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] TecnicoApplicationService - buscaChamadosPorPrioridade");
		Prioridade prioridade = Prioridade.validaPrioridade(prioridadeRequest.prioridade()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para esta Prioridade."));
		List<Chamado> Chamados = chamadoRepository.buscaChamadosPorPrioridade(prioridade);
		log.info("[finaliza] TecnicoApplicationService - buscaChamadosPorPrioridade");
		return ChamadoDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoDetalhadoResponse> buscaChamadosPorStatus(BuscaStatusRequest statusRequest) {
		log.info("[inicia] TecnicoApplicationService - buscaChamadosPorStatus");
		StatusChamado status = StatusChamado.validaStatus(statusRequest.status())
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para este Status."));
		List<Chamado> Chamados = chamadoRepository.buscaChamadosPorStatus(status);
		log.info("[finaliza] TecnicoApplicationService - buscaChamadosPorStatus");
		return ChamadoDetalhadoResponse.converte(Chamados);
	}

	@Override
	public void mudaPrioridadeParaMedia(UUID idChamado) {
		log.info("[inicia] TecnicoApplicationService - mudaPrioridadeParaMedia");
		Chamado chamado = detalhaChamado(idChamado);
		chamado.mudaPrioridadeMedia();
		chamadoRepository.salva(chamado);
		log.info("[finaliza] TecnicoApplicationService - mudaPrioridadeParaMedia");
	}

}
