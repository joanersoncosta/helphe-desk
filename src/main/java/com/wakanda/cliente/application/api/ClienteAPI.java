package com.wakanda.cliente.application.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/v1/cliente")
public interface ClienteAPI {
	
	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	ClienteIdResponse cadastraNovoCliente(@RequestBody @Valid ClienteNovoRequest clienteRequest);

	@GetMapping(value = "/{idCliente}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	ClienteDetalhadoResponse buscaClientePorId(@RequestParam(name = "email", required = true) String email, @PathVariable(value = "idCliente") UUID idCliente);

	@GetMapping(value = "/restrito/busca-clientes")
	@ResponseStatus(value = HttpStatus.OK)
	List<ClienteListResponse> buscaTodosOsClientes(@RequestParam(name = "email", required = true) String email);

	@PatchMapping(path = "/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaDadosDoCliente(@RequestParam(name = "email", required = true) String email, @RequestBody @Valid EditaClienteRequest clienteRequest);

}
