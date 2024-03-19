package com.wakanda.chamado.domain.enuns;

import java.util.Arrays;
import java.util.Optional;

public enum Prioridade {
	BAIXA("BAIXA"), MEDIA("MEDIA"), ALTA("ALTA");

	private String prioridade;

	Prioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getPrioridade() {
		return this.prioridade;
	}

	public static Optional<Prioridade> validaPrioridade(String prioridade) {
		return Arrays.stream(values())
				.filter(valorCorrespondente -> valorCorrespondente.getPrioridade().equals(prioridade.toUpperCase()))
				.findFirst();
	}

}
