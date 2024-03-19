package com.wakanda.chamado.application.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wakanda.chamado.application.api.request.ChamadoRequest;
import com.wakanda.chamado.application.api.response.ChamadoIdResponse;
import com.wakanda.chamado.application.repository.ChamadoRepository;
import com.wakanda.chamado.application.service.ChamadoService;
import com.wakanda.chamado.domain.Chamado;
import com.wakanda.cliente.application.repository.ClienteRepository;
import com.wakanda.cliente.domain.Cliente;
import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.repository.TecnicoRepository;
import com.wakanda.tecnico.domain.Tecnico;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChamadoApllicationService implements ChamadoService {
	private final ClienteRepository ClienteRepository;
	private final ChamadoRepository chamadoRepository;
	private final TecnicoRepository tecnicoRepository;
	
	@Override
	public ChamadoIdResponse criaNovoChamado(String email, ChamadoRequest chamadoRequest) {
		log.info("[inicia] TecnicoApplicationService - cadastraNovoTecnico");
		Cliente cliente = detalhaClientePorEmail(email);
		log.info("[cliente] {}", cliente);
		Tecnico tecnico = detalhaTecnicoPorId(chamadoRequest.idTecnico());
		log.info("[tecnico] {}", tecnico);
		Chamado Chamado = chamadoRepository.salva(new Chamado(cliente.getIdCliente(), chamadoRequest));
		log.info("[finaliza] TecnicoApplicationService - cadastraNovoTecnico");
		return ChamadoIdResponse.builder().idChamado(Chamado.getIdChamado()).build();
	}
	
	private Tecnico detalhaTecnicoPorId(UUID idTecnico) {
		log.info("[inicia] TecnicoApplicationService - detalhaTecnicoPorId");
		Tecnico tecnico = tecnicoRepository.buscaTecnicoPorId(idTecnico)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
		log.info("[finaliza] TecnicoApplicationService - detalhaTecnicoPorId");
		return tecnico;
	}
	
	private Cliente detalhaClientePorEmail(String email) {
		log.info("[inicia] TecnicoApplicationService - detalhaClientePorEmail");
		Cliente cliente = ClienteRepository.detalhaClientePorEmail(email);
		log.info("[finaliza] TecnicoApplicationService - detalhaClientePorEmail");
		return cliente;
	}

}