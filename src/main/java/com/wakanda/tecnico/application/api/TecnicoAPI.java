package com.wakanda.tecnico.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/tecnico")
public interface TecnicoAPI {

	@PostMapping(path = "/admin/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	TecnicoIdResponse cadastraNovoTecnico(@RequestParam(name = "token", required = true) String token, @RequestBody @Valid TecnicoNovoRequest tecnicoRequest);

}
