package com.wakanda.chamado.application.api.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.wakanda.chamado.domain.Chamado;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ChamadoListDetalhadoResponse {

	private UUID idChamado;
	private UUID idTecnico;
	private UUID idCliente;
	private Prioridade prioridade;
	private StatusChamado status;
	private String titulo;
	private String observacoes;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;

	public ChamadoListDetalhadoResponse(Chamado chamado) {
		this.idChamado = chamado.getIdChamado();
		this.idTecnico = chamado.getIdTecnico();
		this.idCliente = chamado.getIdCliente();
		this.prioridade = chamado.getPrioridade();
		this.status = chamado.getStatus();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
	}

	public static List<ChamadoListDetalhadoResponse> converte(List<Chamado> chamado) {
		return chamado.stream().map(ChamadoListDetalhadoResponse::new).collect(Collectors.toList());
	}

}