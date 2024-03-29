package com.wakanda.cliente.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.request.EditaClienteRequest;
import com.wakanda.cliente.application.api.response.ClienteDetalhadoResponse;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;
import com.wakanda.cliente.application.api.response.ClienteListResponse;

@RestController
@RequestMapping("/v1/cliente")
public interface ClienteAPI {
	
	@PostMapping(path = "/public/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	ClienteIdResponse cadastraNovoCliente(@RequestBody @Valid ClienteNovoRequest clienteRequest);

	@GetMapping(value = "/public/{idCliente}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	ClienteDetalhadoResponse buscaClientePorId(@RequestHeader("Authorization") String token, @PathVariable(value = "idCliente") UUID idCliente);

	@GetMapping(value = "/restrito/busca-clientes")
	@ResponseStatus(value = HttpStatus.OK)
	List<ClienteListResponse> buscaTodosOsClientes(@RequestHeader("Authorization") String token);

	@PatchMapping(path = "/public/{idCliente}/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaDadosDoCliente(@RequestHeader("Authorization") String token, @PathVariable(value = "idCliente") UUID idCliente, @RequestBody @Valid EditaClienteRequest clienteRequest);

	@DeleteMapping(path = "/public/{idCliente}/deleta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaCliente(@RequestHeader("Authorization") String token, @PathVariable(value = "idCliente") UUID idCliente);

}
