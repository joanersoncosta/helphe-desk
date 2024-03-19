package com.wakanda.tecnico.infra.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.wakanda.handler.APIException;
import com.wakanda.tecnico.application.repository.TecnicoRepository;
import com.wakanda.tecnico.domain.Tecnico;
import com.wakanda.tecnico.infra.TecnicoSpringDBMongoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class TecnicoInfraRepository implements TecnicoRepository {
	private final TecnicoSpringDBMongoRepository tecnicoSpringDBMongoRepository;

	@Override
	public Tecnico salva(Tecnico tecnico) {
		log.info("[start] TecnicoInfraRepository - salva");
		try {
			tecnicoSpringDBMongoRepository.save(tecnico);
		}catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Tecnico já cadastrado.");
		}
		log.info("[finish] TecnicoInfraRepository - salva");
		return tecnico;
	}

	@Override
	public Optional<Tecnico> buscaTecnicoPorId(UUID idTecnico) {
		log.info("[start] TecnicoInfraRepository - buscaTecnicoPorId");
		Optional<Tecnico> tecnico = tecnicoSpringDBMongoRepository.findById(idTecnico);
		log.info("[finish] TecnicoInfraRepository - buscaTecnicoPorId");
		return tecnico;
	}

	@Override
	public List<Tecnico> buscaTecnicos() {
		log.info("[start] TecnicoInfraRepository - buscaTecnicos");
		List<Tecnico> tecnicos = tecnicoSpringDBMongoRepository.findAll();
		log.info("[finish] TecnicoInfraRepository - buscaTecnicos");
		return tecnicos;
	}

}
