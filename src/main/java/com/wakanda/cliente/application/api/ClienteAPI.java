package com.wakanda.cliente.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.cliente.application.api.request.ClienteNovoRequest;
import com.wakanda.cliente.application.api.response.ClienteIdResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/v1/cliente")
public interface ClienteAPI {
	
	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	ClienteIdResponse cadastraNovoCliente(@RequestBody @Valid ClienteNovoRequest clienteRequest);

}
