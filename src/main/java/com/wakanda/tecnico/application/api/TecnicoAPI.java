package com.wakanda.tecnico.application.api;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;

@RestController
@RequestMapping("/v1/tecnico")
public interface TecnicoAPI {

	@PostMapping(path = "/admin/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	TecnicoIdResponse cadastraNovoTecnico(@RequestParam(name = "email", required = true) String email,
			@RequestBody @Valid TecnicoNovoRequest tecnicoRequest);

	@GetMapping(value = "/{idTecnico}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	TecnicoDetalhadoResponse buscaTecnicoPorId(@RequestParam(name = "email", required = true) String email,
			@PathVariable(value = "idTecnico") UUID idTecnico);

	@GetMapping(value = "/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<TecnicoDetalhadoResponse> buscaTecnicos(@RequestParam(name = "email", required = true) String email);

	@PatchMapping(path = "/restrito/{idTecnico}/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaDadosDoTecnico(@RequestParam(name = "email", required = true) String email,
			@PathVariable(value = "idTecnico") UUID idTecnico, @RequestBody @Valid EditaTecnicoRequest tecnicoRequest);

	@DeleteMapping(path = "/restrito/{idTecnico}/deleta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaTecnico(@RequestParam(name = "email", required = true) String email,
			@PathVariable(value = "idTecnico") UUID idTecnico);

}
