package com.wakanda.chamado.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.wakanda.chamado.application.repository.ChamadoRepository;
import com.wakanda.chamado.domain.Chamado;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ChamadoInfraRepository implements ChamadoRepository {
	private final ChamadoSpringDBMongoRepository chamadoSpringDBMongoRepository;
	
	@Override
	public Chamado salva(Chamado chamado) {
		log.info("[start] ChamadoInfraRepository - salva");
		Chamado novoChamado = chamadoSpringDBMongoRepository.save(chamado);
		log.info("[finish] ChamadoInfraRepository - salva");
		return novoChamado;
	}

	@Override
	public Optional<Chamado> buscaChamadoPorId(UUID idChamado) {
		log.info("[start] ChamadoInfraRepository - buscaChamadoPorId");
		Optional<Chamado> chamado = chamadoSpringDBMongoRepository.findById(idChamado);
		log.info("[finish] ChamadoInfraRepository - buscaChamadoPorId");
		return chamado;
	}

	@Override
	public List<Chamado> buscaChamados() {
		log.info("[start] ChamadoInfraRepository - buscaChamados");
		List<Chamado> chamados = chamadoSpringDBMongoRepository.findAll();
		log.info("[finish] ChamadoInfraRepository - buscaChamados");
		return chamados;
	}

	@Override
	public void deletaChamado(Chamado chamado) {
		log.info("[start] ChamadoInfraRepository - deletaChamado");
		chamadoSpringDBMongoRepository.delete(chamado);
		log.info("[finish] ChamadoInfraRepository - deletaChamado");
	}

}
