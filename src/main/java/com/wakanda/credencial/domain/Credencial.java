package com.wakanda.credencial.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Credencial")
public class Credencial implements UserDetails {
	@MongoId
//	private UUID idCredencial;
	@Getter
	private String usuario;

	@NotNull
	@Size(max = 60)
	private String senha;

	@NotNull
	private Perfil perfil;

	@Getter
	private boolean validado;

	public Credencial(String usuario, String senha, Perfil nome) {
		this.usuario = usuario;
		var encriptador = new BCryptPasswordEncoder();
		this.senha = encriptador.encode(senha);
		this.perfil = nome;
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
		if (perfil.getNome().equals("ADMIN"))
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_TECNICO"),
					new SimpleGrantedAuthority("ROLE_CLIENTE"));
		else if (perfil.getNome().equals("TECNICO")) {
			return List.of(new SimpleGrantedAuthority("ROLE_TECNICO"), new SimpleGrantedAuthority("ROLE_CLIENTE"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
		}
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
}
