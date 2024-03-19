package com.wakanda.chamado.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;
import com.wakanda.handler.APIException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Chamado")
public class Chamado {

	@Id
	private UUID idChamado;
	@Indexed
	private UUID idTecnico;
	@Indexed
	private UUID idCliente;
	private Prioridade prioridade;
	private StatusChamado status;
	private String titulo;
	private String observacoes;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;

	private Prioridade retornaPrioridade(String prioridade) {
		return Prioridade.validaPrioridade(prioridade).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Prioridade inválida, digite novamente."));
	}
	
	private StatusChamado retornaStatusChamado(String status) {
		return StatusChamado.validaStatus(status).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Status inválido, digite novamente."));
	}
	
}
