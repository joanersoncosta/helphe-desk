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
import com.wakanda.chamado.application.api.response.ChamadoListDetalhadoResponse;
import com.wakanda.chamado.application.repository.ChamadoRepository;
import com.wakanda.chamado.application.service.ChamadoService;
import com.wakanda.chamado.domain.Chamado;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;
import com.wakanda.cliente.application.repository.ClienteRepository;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.enuns.TipoPerfil;
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
	private final CredencialService credencialService;

	@Override
	public ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest) {
		log.info("[inicia] ChamadoApllicationService - cadastraNovoTecnico");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		Tecnico tecnico = detalhaTecnicoPorId(chamadoRequest.idTecnico());
		log.info("[tecnico] {}", tecnico);
		Chamado Chamado = chamadoRepository
				.salva(new Chamado(cliente.getIdCliente(), chamadoRequest, cliente.getEmail(), tecnico.getEmail()));
		log.info("[finaliza] ChamadoApllicationService - cadastraNovoTecnico");
		return ChamadoIdResponse.builder().idChamado(Chamado.getIdChamado()).build();
	}

	private Tecnico detalhaTecnicoPorId(UUID idTecnico) {
		log.info("[inicia] ChamadoApllicationService - detalhaTecnicoPorId");
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado para este ID."));
		log.info("[finaliza] ChamadoApllicationService - detalhaTecnicoPorId");
		return tecnico;
	}

	private Cliente detalhaClientePorEmail(String email) {
		log.info("[inicia] ChamadoApllicationService - detalhaClientePorEmail");
		Cliente cliente = ClienteRepository.detalhaClientePorEmail(email);
		log.info("[finaliza] ChamadoApllicationService - detalhaClientePorEmail");
		return cliente;
	}

	@Override
	public ChamadoDetalhadoResponse buscaChamadoPorId(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadoPorId");
		log.info("[idChamado] {}", idChamado);
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		Chamado chamado = chamadoRepository.buscaChamadoPorId(idChamado)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Chamado não encontrado para este ID."));
		chamado.pertenceAoUsuario(credencialUsuario.getUsuario());
		log.info("[finaliza] ChamadoApllicationService - buscaChamadoPorId");
		return ChamadoDetalhadoResponse.converte(chamado);
	}

	private Chamado detalhaChamado(UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - detalhaChamado");
		Chamado chamado = chamadoRepository.buscaChamadoPorId(idChamado)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Chamado não encontrado para este ID."));
		log.info("[finaliza] ChamadoApllicationService - detalhaChamado");
		return chamado;
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamados(String email) {
		log.info("[inicia] ChamadoApllicationService - buscaChamados");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		credencialUsuario.validaCredencialAdmin();
		List<Chamado> Chamados = chamadoRepository.buscaChamados();
		log.info("[finaliza] ChamadoApllicationService - buscaChamados");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosPorPrioridade(String email,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosPorPrioridade");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		credencialUsuario.validaCredencialAdmin();
		Prioridade prioridade = Prioridade.validaPrioridade(prioridadeRequest.prioridade()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para esta Prioridade."));
		List<Chamado> Chamados = chamadoRepository.buscaChamadosPorPrioridade(prioridade);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosPorPrioridade");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorPrioridade(String email, UUID idTecnico,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoTecnicoPorPrioridade");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = detalhaTecnicoPorId(idTecnico);
		Prioridade prioridade = Prioridade.validaPrioridade(prioridadeRequest.prioridade()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para esta Prioridade."));
		tecnico.pertenceAoTecnico(emailTecnico);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoTecnicoPorPrioridade(tecnico.getIdTecnico(),
				prioridade);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoTecnicoPorPrioridade");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorPrioridade(String email, UUID idCliente,
			BuscaPrioridadeRequest prioridadeRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoClientePorPrioridade");
		Cliente emailCliente = detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = ClienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado para este ID."));
		Prioridade prioridade = Prioridade.validaPrioridade(prioridadeRequest.prioridade()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para esta Prioridade."));
		cliente.pertenceAoCliente(emailCliente);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoClientePorPrioridade(cliente.getIdCliente(),
				prioridade);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoClientePorPrioridade");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosPorStatus(String email, BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosPorStatus");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		credencialUsuario.validaCredencialAdmin();
		StatusChamado status = StatusChamado.validaStatus(statusRequest.status()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para este Status."));
		List<Chamado> Chamados = chamadoRepository.buscaChamadosPorStatus(status);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosPorStatus");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorStatus(String email, UUID idTecnico,
			BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoTecnicoPorStatus");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = detalhaTecnicoPorId(idTecnico);
		StatusChamado status = StatusChamado.validaStatus(statusRequest.status()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para este Status."));
		tecnico.pertenceAoTecnico(emailTecnico);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoTecnicoPorStatus(tecnico.getIdTecnico(), status);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoTecnicoPorStatus");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}
	
	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorStatus(String email, UUID idCliente,
			BuscaStatusRequest statusRequest) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoClientePorStatus");
		Cliente emailCliente = detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = ClienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado para este ID."));
		StatusChamado status = StatusChamado.validaStatus(statusRequest.status()).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Nenhum Chamado encontrado para este Status."));
		cliente.pertenceAoCliente(emailCliente);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoClientePorStatus(cliente.getIdCliente(), status);
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoClientePorStatus");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoCliente(String email, UUID idCliente) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoCliente");
		Cliente emailCliente = detalhaClientePorEmail(email);
		log.info("[emailCliente] {}", emailCliente);
		log.info("[idCliente] {}", idCliente);
		Cliente cliente = ClienteRepository.detalhaClientePorId(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente não encontrado para este ID."));
		cliente.pertenceAoCliente(emailCliente);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoCliente(cliente.getIdCliente());
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoCliente");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnico(String email, UUID idTecnico) {
		log.info("[inicia] ChamadoApllicationService - buscaChamadosDoTecnico");
		Tecnico emailTecnico = tecnicoRepository.buscaTecnicoPorEmail(email);
		log.info("[emailTecnico] {}", emailTecnico);
		log.info("[idTecnico] {}", idTecnico);
		Tecnico tecnico = detalhaTecnicoPorId(idTecnico);
		tecnico.pertenceAoTecnico(emailTecnico);
		List<Chamado> Chamados = chamadoRepository.buscaChamadosDoTecnico(tecnico.getIdTecnico());
		log.info("[finaliza] ChamadoApllicationService - buscaChamadosDoTecnico");
		return ChamadoListDetalhadoResponse.converte(Chamados);
	}

	@Override
	public void editaChamadoPorId(String email, UUID idChamado, EditaChamadoRequest chamadoRequest) {
		log.info("[inicia] ChamadoApllicationService - editaChamadoPorId");
		Cliente clienteEmail = detalhaClientePorEmail(email);
		log.info("[cliente] {}", clienteEmail);
		log.info("[idChamado] {}", idChamado);
		Chamado chamado = detalhaChamado(idChamado);
		chamado.pertenceAoCliente(clienteEmail);
		chamado.edita(chamadoRequest);
		chamadoRepository.salva(chamado);
		log.info("[finaliza] ChamadoApllicationService - editaChamadoPorId");
	}

	@Override
	public void mudaPrioridadeParaMedia(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - mudaPrioridadeParaMedia");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		log.info("[idChamado] {}", idChamado);
		credencialUsuario.validaCredencialUsuario();
		Chamado chamado = detalhaChamado(idChamado);
		if (!credencialUsuario.getPerfil().equals(TipoPerfil.ADMIN)) {
			chamado.pertenceAoTecnico(credencialUsuario.getUsuario());
		}
		chamado.mudaPrioridadeParaMedia();
		chamadoRepository.salva(chamado);
		log.info("[finaliza] ChamadoApllicationService - mudaPrioridadeParaMedia");
	}

	@Override
	public void mudaPrioridadeParaAlta(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - mudaPrioridadeParaAlta");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		log.info("[idChamado] {}", idChamado);
		credencialUsuario.validaCredencialUsuario();
		Chamado chamado = detalhaChamado(idChamado);
		if (!credencialUsuario.getPerfil().equals(TipoPerfil.ADMIN)) {
			chamado.pertenceAoTecnico(credencialUsuario.getUsuario());
		}
		chamado.mudaPrioridadeParaAlta();
		chamadoRepository.salva(chamado);
		log.info("[finaliza] ChamadoApllicationService - mudaPrioridadeParaAlta");
	}

	@Override
	public void mudaStatusParaAndamento(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - mudaStatusParaAndamento");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		log.info("[idChamado] {}", idChamado);
		credencialUsuario.validaCredencialUsuario();
		Chamado chamado = detalhaChamado(idChamado);
		if (!credencialUsuario.getPerfil().equals(TipoPerfil.ADMIN)) {
			chamado.pertenceAoTecnico(credencialUsuario.getUsuario());
		}
		chamado.mudaStatusParaAndamento();
		chamadoRepository.salva(chamado);
		log.info("[finaliza] ChamadoApllicationService - mudaStatusParaAndamento");
	}

	@Override
	public void mudaStatusParaEncerrado(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - mudaStatusParaEncerrado");
		Credencial credencialUsuario = buscaCredencialPorUsuario(email);
		log.info("[credencialUsuario] {}", credencialUsuario);
		log.info("[idChamado] {}", idChamado);
		credencialUsuario.validaCredencialUsuario();
		Chamado chamado = detalhaChamado(idChamado);
		if (!credencialUsuario.getPerfil().equals(TipoPerfil.ADMIN)) {
			chamado.pertenceAoTecnico(credencialUsuario.getUsuario());
		}
		chamado.mudaStatusParaEncerrado();
		chamadoRepository.salva(chamado);
		log.info("[finaliza] ChamadoApllicationService - mudaStatusParaEncerrado");
	}

	@Override
	public void deletaChamadoPorId(String email, UUID idChamado) {
		log.info("[inicia] ChamadoApllicationService - deletaChamadoPorId");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		log.info("[idChamado] {}", idChamado);
		Chamado chamado = detalhaChamado(idChamado);
		chamado.pertenceAoCliente(cliente);
		chamadoRepository.deletaChamado(chamado);
		log.info("[finaliza] ChamadoApllicationService - deletaChamadoPorId");
	}

	private Credencial buscaCredencialPorUsuario(String email) {
		log.info("[inicia] ChamadoApllicationService - buscaCredencialPorUsuario");
		Credencial credencialUsuario = credencialService.buscaCredencialPorUsuario(email);
		log.info("[finaliza] ChamadoApllicationService - buscaCredencialPorUsuario");
		return credencialUsuario;
	}

}
