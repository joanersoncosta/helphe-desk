package com.wakanda.tecnico.application.api.response;

import java.util.UUID;

import com.wakanda.cliente.domain.enuns.Sexo;
import com.wakanda.tecnico.domain.Tecnico;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class TecnicoDetalhadoResponse {

	private UUID idCliente;
	private String nome;
	private String email;
	private Sexo sexo;

	private TecnicoDetalhadoResponse(Tecnico tecnico) {
		this.idCliente = tecnico.getIdTecnico();
		this.nome = tecnico.getNome();
		this.email = tecnico.getEmail();
		this.sexo = tecnico.getSexo();
	}

	public static TecnicoDetalhadoResponse converteClienteParaResponse(Tecnico tecnico) {
		return new TecnicoDetalhadoResponse(tecnico);
	}
}
