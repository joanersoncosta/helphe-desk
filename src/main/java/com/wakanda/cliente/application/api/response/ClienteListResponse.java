package com.wakanda.cliente.application.api.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.wakanda.cliente.domain.Cliente;
import com.wakanda.cliente.domain.enuns.Sexo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ClienteListResponse {
	private UUID idCliente;
	private String nome;
	private String email;
	private Sexo sexo;

	public ClienteListResponse(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.sexo = cliente.getSexo();
	}
	
	public static List<ClienteListResponse> converte(List<Cliente> clientes){
		return clientes.stream().map(ClienteListResponse::new).collect(Collectors.toList());
	}
}
