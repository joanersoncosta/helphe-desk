package com.wakanda.cliente.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.domain.enuns.Sexo;
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
@Document(collection = "Cliente")
public class Cliente {

	@Id
	private UUID idCliente;
	@NotBlank
	private String nome;
	@NotBlank
	@Indexed(unique = true)
	@Size(min = 11, max = 11, message = "número do registro de contribuinte individual brasileiro (CPF) inválido")
//	@CPF
	private String cpf;
	@Email
	@NotBlank
	@Indexed(unique = true)
	private String email;
	@NotNull
	private Sexo sexo;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataHoraDaUltimaAlteracao;

	public Cliente(ClienteNovoRequest request) {
		this.idCliente = UUID.randomUUID();
		this.nome = request.nome();
		this.cpf = request.cpf();
		this.email = request.email();
		this.sexo = retornaSexoValido(request.sexo());
		this.dataCadastro = LocalDateTime.now();
	}

	private Sexo retornaSexoValido(String sexo) {
		return Sexo.validaSexo(sexo)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Sexo inválido, digite novamente."));
	}

	public void pertenceAoCliente(UUID idCliente) {
		if (!this.idCliente.equals(idCliente)) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

	public void editaDadosDoCliente(EditaClienteRequest request) {
		this.nome = request.nome();
		this.sexo = retornaSexoValido(request.sexo());
		this.dataHoraDaUltimaAlteracao = LocalDateTime.now();
	}

}
