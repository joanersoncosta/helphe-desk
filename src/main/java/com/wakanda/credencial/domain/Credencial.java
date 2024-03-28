package com.wakanda.credencial.domain;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wakanda.credencial.domain.enuns.TipoPerfil;
import com.wakanda.handler.APIException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Credencial")
public class Credencial implements UserDetails {

	@MongoId(targetType = FieldType.STRING)
	private String idCredencial;
	@Getter
	@Email
	private String usuario;

	@NotNull
	@Size(max = 60)
	private String senha;

	@NotNull
//	private Perfil perfil;
	@Getter
	private TipoPerfil perfil;

	@Getter
	private boolean validado;

	public Credencial(String usuario, String senha, TipoPerfil tipoPerfil) {
		this.usuario = usuario;
		var encriptador = new BCryptPasswordEncoder();
		this.senha = encriptador.encode(senha);
		this.perfil = tipoPerfil;
		this.validado = true;
	}

	public void encriptaSenha() {
		var encriptador = new BCryptPasswordEncoder();
		this.senha = encriptador.encode(this.senha);
	}

	public void validaCredencial() {
		this.validado = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (perfil.equals(TipoPerfil.ADMIN)) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_TECNICO"),
					new SimpleGrantedAuthority("ROLE_CLIENTE"));
		} else if (perfil.equals(TipoPerfil.TECNICO)) {
			return List.of(new SimpleGrantedAuthority("ROLE_TECNICO"), new SimpleGrantedAuthority("ROLE_CLIENTE"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private static final long serialVersionUID = 1L;

	public void validaCredencialAdmin() {
		if(!perfil.equals(TipoPerfil.ADMIN)) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

	public void validaCredencialUsuario() {
		if(!(perfil.equals(TipoPerfil.ADMIN) || perfil.equals(TipoPerfil.TECNICO))) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}
}
