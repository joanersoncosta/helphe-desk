package com.wakanda.cliente.domain;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.wakanda.cliente.domain.enuns.Sexo;
import com.wakanda.handler.APIException;

import jakarta.validation.constraints.Email;
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
@Document(collection = "Cliente")
public class Cliente {
	
	@Id
	private Integer idPessoa;
	@NotBlank
	private String nome;
	@NotBlank
	@Indexed(unique = true)
	@CPF
	private String cpf;
	@Email
	@NotBlank
	@Indexed(unique = true)
	private String email;
	@NotNull
	private Sexo sexo;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataHoraDaUltimaAlteracao;

	private Sexo retornaSexoValido(String sexo) {
		return Sexo.validaSexo(sexo)
	            .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Sexo inválido, digite novamente."));
	}
	
}
