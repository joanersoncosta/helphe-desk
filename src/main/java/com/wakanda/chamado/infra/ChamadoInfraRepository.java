package com.wakanda.chamado.infra;

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

}
