package com.wakanda.cliente.application.api.response;

import java.util.UUID;

import com.wakanda.cliente.domain.Cliente;
import com.wakanda.cliente.domain.enuns.Sexo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ClienteDetalhadoResponse {
	private UUID idCliente;
	private String nome;
	private String email;
	private Sexo sexo;

	public ClienteDetalhadoResponse(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.sexo = cliente.getSexo();
	}
	
	public static ClienteDetalhadoResponse converteClienteParaResponse(Cliente cliente) {
		return new ClienteDetalhadoResponse(cliente);
	}

}
