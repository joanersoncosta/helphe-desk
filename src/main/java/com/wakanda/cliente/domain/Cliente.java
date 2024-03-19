package com.wakanda.cliente.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.domain.enuns.Sexo;
import com.wakanda.handler.APIException;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	private UUID idPessoa;
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
		this.idPessoa = UUID.randomUUID();
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

}
