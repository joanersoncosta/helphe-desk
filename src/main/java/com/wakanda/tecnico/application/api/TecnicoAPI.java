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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.tecnico.application.api.request.EditaTecnicoRequest;
import com.wakanda.tecnico.application.api.request.TecnicoNovoRequest;
import com.wakanda.tecnico.application.api.response.TecnicoDetalhadoResponse;
import com.wakanda.tecnico.application.api.response.TecnicoIdResponse;
import com.wakanda.tecnico.application.api.response.TecnicoListDetalhadoResponse;

@RestController
@RequestMapping("/v1/tecnico")
public interface TecnicoAPI {

	@PostMapping(value = "/admin/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	TecnicoIdResponse cadastraNovoTecnico(@RequestHeader("Authorization") String token,
			@RequestBody @Valid TecnicoNovoRequest tecnicoRequest);

	@GetMapping(value = "/public/{idTecnico}/busca")
	@ResponseStatus(value = HttpStatus.OK)
	TecnicoDetalhadoResponse buscaTecnicoPorId(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idTecnico") UUID idTecnico);

	@GetMapping(value = "/public/busca")
	@ResponseStatus(value = HttpStatus.OK)
	List<TecnicoListDetalhadoResponse> buscaTecnicos(@RequestHeader("Authorization") String token);

	@PatchMapping(path = "/public/{idTecnico}/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaDadosDoTecnico(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idTecnico") UUID idTecnico, @RequestBody @Valid EditaTecnicoRequest tecnicoRequest);

	@DeleteMapping(path = "/public/{idTecnico}/deleta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaTecnico(@RequestHeader("Authorization") String token,
			@PathVariable(value = "idTecnico") UUID idTecnico);

}
