package com.wakanda.chamado.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@Indexed
	@NotBlank
	private String emailCliente;
	@Indexed
	@NotBlank
	private String emailTecnico;
	@NotBlank
	private Prioridade prioridade;
	private StatusChamado status;
	@NotBlank
	private String titulo;
	@NotBlank
	private String observacoes;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;

	public Chamado(UUID idCliente, ChamadoRequest chamadoRequest, String emailCliente, String emailTecnico) {
		this.idChamado = UUID.randomUUID();
		this.idTecnico = chamadoRequest.idTecnico();
		this.idCliente = idCliente;
		this.emailCliente = emailCliente;
		this.emailTecnico = emailTecnico;
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

	public void edita(EditaChamadoRequest chamadoRequest) {
		this.titulo = chamadoRequest.titulo();
		this.observacoes = chamadoRequest.observacoes();
	}

	public void mudaPrioridadeParaMedia() {
		this.prioridade = Prioridade.MEDIA;
	}

	public void mudaPrioridadeParaAlta() {
		this.prioridade = Prioridade.ALTA;
	}

	public void mudaStatusParaAndamento() {
		this.status = StatusChamado.ANDAMENTO;
	}

	public void mudaStatusParaEncerrado() {
		this.status = StatusChamado.ENCERRADO;
		this.dataFechamento = LocalDate.now();
	}

	public void pertenceAoCliente(Cliente cliente) {
		if (!this.idCliente.equals(cliente.getIdCliente())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}
	
	public void pertenceAoTecnico(String emailUsuario) {
		if (!emailTecnico.equals(emailUsuario)) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

	public void pertenceAoUsuario(String emailUsuario) {
		if (!(emailCliente.equals(emailUsuario) || emailTecnico.equals(emailUsuario))) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

}
