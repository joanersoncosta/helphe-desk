package com.wakanda.chamado.application.service;

import java.util.List;
import java.util.UUID;

import com.wakanda.chamado.application.api.request.BuscaPrioridadeRequest;
import com.wakanda.chamado.application.api.request.BuscaStatusRequest;
import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoDetalhadoResponse;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;

public interface ChamadoService {

	ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest);

	ChamadoDetalhadoResponse buscaChamadoPorId(UUID idChamado);

	List<ChamadoDetalhadoResponse> buscaChamados();

	List<ChamadoDetalhadoResponse> buscaChamadosPorPrioridade(BuscaPrioridadeRequest prioridadeRequest);

	List<ChamadoDetalhadoResponse> buscaChamadosPorStatus(BuscaStatusRequest statusRequest);

	List<ChamadoDetalhadoResponse> buscaChamadosDoCliente(String email);

	List<ChamadoDetalhadoResponse> buscaChamadosDoTecnico(String email);

	void editaChamadoPorId(String email, UUID idChamado, EditaChamadoRequest chamadoRequest);

	void mudaPrioridadeParaMedia(UUID idChamado);

	void mudaPrioridadeParaAlta(UUID idChamado);

	void mudaStatusParaAndamento(UUID idChamado);

	void mudaStatusParaEncerrado(UUID idChamado);

	void deletaChamadoPorId(String email, UUID idChamado);

}
