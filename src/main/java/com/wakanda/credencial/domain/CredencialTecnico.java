package com.wakanda.credencial.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;

import lombok.Getter;

@Getter
public class CredencialTecnico {
	@NotBlank
	private String email;
	@Size(min = 6, max = 9, message = "A senha deve ter no mínimo 6 e no máximo 9 caracteres ")
	private String senha;
	private Perfil nome;
	
	public CredencialTecnico(TecnicoNovoRequest tecnico) {
		this.email = tecnico.email();
		this.senha = tecnico.senha();
		this.nome = new Perfil("TECNICO");
	}
}
