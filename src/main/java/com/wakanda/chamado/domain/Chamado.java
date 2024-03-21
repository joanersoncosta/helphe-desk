package com.wakanda.chamado.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.request.EditaChamadoRequest;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.handler.APIException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private UUID idTecnico;
	@Indexed
	private UUID idCliente;
	@NotBlank
	private Prioridade prioridade;
	private StatusChamado status;
	@NotBlank
	private String titulo;
	@NotBlank
	private String observacoes;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;

	public Chamado(UUID idCliente, ChamadoRequest chamadoRequest) {
		this.idChamado = UUID.randomUUID();
		this.idTecnico = chamadoRequest.idTecnico();
		this.idCliente = idCliente;
		this.prioridade = retornaPrioridade(chamadoRequest.prioridade());
		this.status = StatusChamado.ABERTO;
		this.titulo = chamadoRequest.titulo();
		this.observacoes = chamadoRequest.observacoes();
		this.dataAbertura = LocalDate.now();
	}

	private Prioridade retornaPrioridade(String prioridade) {
		return Prioridade.validaPrioridade(prioridade).orElseThrow(
				() -> APIException.build(HttpStatus.BAD_REQUEST, "Prioridade inválida, digite novamente."));
	}

	private StatusChamado retornaStatusChamado(String status) {
		return StatusChamado.validaStatus(status)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Status inválido, digite novamente."));
	}

	public void pertenceAoCliente(Cliente cliente) {
		if (!idCliente.equals(cliente.getIdCliente())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Cliente não autorizado.");
		}
	}

	public void edita(EditaChamadoRequest chamadoRequest) {
		this.titulo = chamadoRequest.titulo();
		this.observacoes = chamadoRequest.observacoes();
	}

	public void mudaPrioridadeMedia() {
		this.prioridade = Prioridade.MEDIA;
	}

}
