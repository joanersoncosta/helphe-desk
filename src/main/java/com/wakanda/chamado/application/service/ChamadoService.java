package com.wakanda.chamado.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
import com.wakanda.chamado.application.api.response.ChamadoListDetalhadoResponse;

public interface ChamadoService {

	ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest);

	ChamadoDetalhadoResponse buscaChamadoPorId(String email, UUID idChamado);

	List<ChamadoListDetalhadoResponse> buscaChamados(String email);

	List<ChamadoListDetalhadoResponse> buscaChamadosPorPrioridade(String email,
			BuscaPrioridadeRequest prioridadeRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorPrioridade(String email, UUID idTecnico,
			BuscaPrioridadeRequest prioridadeRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorPrioridade(String email, UUID idCliente,
			BuscaPrioridadeRequest prioridadeRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosPorStatus(String email, BuscaStatusRequest statusRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnicoPorStatus(String email, UUID idTecnico,
			BuscaStatusRequest statusRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoClientePorStatus(String email, UUID idCliente,
			BuscaStatusRequest statusRequest);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoCliente(String email, UUID idCliente);

	List<ChamadoListDetalhadoResponse> buscaChamadosDoTecnico(String email, UUID idTecnico);

	void editaChamadoPorId(String email, UUID idChamado, EditaChamadoRequest chamadoRequest);

	void mudaPrioridadeParaMedia(String email, UUID idChamado);

	void mudaPrioridadeParaAlta(String email, UUID idChamado);

	void mudaStatusParaAndamento(String email, UUID idChamado);

	void mudaStatusParaEncerrado(String email, UUID idChamado);

	void deletaChamadoPorId(String email, UUID idChamado);

}
