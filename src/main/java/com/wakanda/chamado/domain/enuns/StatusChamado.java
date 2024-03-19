package com.wakanda.chamado.domain.enuns;

import java.util.Arrays;
import java.util.Optional;

public enum StatusChamado {
	ABERTO("ABERTO"), ANDAMENTO("ANDAMENTO"), ENCERRADO("ENCERRADO");

	private String status;

	StatusChamado(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public static Optional<StatusChamado> validaStatus(String status) {
		return Arrays.stream(values())
				.filter(valorCorrespondente -> valorCorrespondente.getStatus().equals(status.toUpperCase()))
				.findFirst();
	}

}
