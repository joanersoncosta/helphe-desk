package com.wakanda.credencial.domain.perfis;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.credencial.domain.Perfil;

import lombok.Getter;

@Getter
public class CredencialCliente {
	@NotBlank
	private String email;
	@Size(min = 6, max = 9, message = "A senha deve ter no mínimo 6 e no máximo 9 caracteres ")
	private String senha;
	private Perfil nome;
	
	public CredencialCliente(ClienteNovoRequest cliente) {
		this.email = cliente.email();
		this.senha = cliente.senha();
		this.nome = new Perfil("CLIENTE");
	}
}
