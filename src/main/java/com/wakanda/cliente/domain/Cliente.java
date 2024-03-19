package com.wakanda.cliente.domain;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wakanda.cliente.domain.enuns.Sexo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	private Sexo sexo;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataHoraDaUltimaAlteracao;

	private Sexo retornaSexo(String sexo) {
		return Sexo.validaSexo(sexo)
	            .orElseThrow();
	}
	
}
