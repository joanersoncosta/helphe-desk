package com.wakanda.credencial.domain.perfis;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wakanda.admin.application.api.request.AdminRequest;
import com.wakanda.credencial.domain.enuns.TipoPerfil;

import lombok.Getter;

@Getter
public class CredencialAdmin {
	@NotBlank
	@Email
	private String email;
	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String senha;
	@NotNull
	private TipoPerfil perfil;
	
	public CredencialAdmin(AdminRequest admin) {
		this.email = admin.email();
		this.senha = admin.senha();
		this.perfil = TipoPerfil.ADMIN;
	}
}
