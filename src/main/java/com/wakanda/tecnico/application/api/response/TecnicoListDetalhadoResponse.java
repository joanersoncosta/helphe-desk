package com.wakanda.tecnico.application.api.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.wakanda.cliente.domain.enuns.Sexo;
import com.wakanda.tecnico.domain.Tecnico;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class TecnicoListDetalhadoResponse {

	private UUID idTecnico;
	private String nome;
	private String email;
	private Sexo sexo;

	private TecnicoListDetalhadoResponse(Tecnico tecnico) {
		this.idTecnico = tecnico.getIdTecnico();
		this.nome = tecnico.getNome();
		this.email = tecnico.getEmail();
		this.sexo = tecnico.getSexo();
	}

	public static List<TecnicoListDetalhadoResponse> converte(List<Tecnico> tecnicos) {
		return tecnicos.stream().map(TecnicoListDetalhadoResponse::new).collect(Collectors.toList());
	}
}
