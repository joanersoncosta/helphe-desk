package com.wakanda.chamado.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.wakanda.chamado.application.repository.ChamadoRepository;
import com.wakanda.chamado.domain.Chamado;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ChamadoInfraRepository implements ChamadoRepository {
	private final ChamadoSpringDBMongoRepository chamadoSpringDBMongoRepository;
	private final MongoTemplate mongoTemplate;

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
	public List<Chamado> buscaChamadosPorPrioridade(Prioridade prioridade) {
		log.info("[start] ChamadoInfraRepository - buscaChamadosPorPrioridade");
		Query query = new Query();
		query.addCriteria(Criteria.where("prioridade").is(prioridade));
		List<Chamado> chamados = mongoTemplate.find(query, Chamado.class);
		log.info("[finish] ChamadoInfraRepository - buscaChamadosPorPrioridade");
		return chamados;
	}

	@Override
	public List<Chamado> buscaChamadosPorStatus(StatusChamado status) {
		log.info("[start] ChamadoInfraRepository - buscaChamadosPorStatus");
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		List<Chamado> chamados = mongoTemplate.find(query, Chamado.class);
		log.info("[finish] ChamadoInfraRepository - buscaChamadosPorStatus");
		return chamados;
	}

	@Override
	public List<Chamado> buscaChamadosDoCliente(UUID idCliente) {
		log.info("[start] ChamadoInfraRepository - buscaChamadosDoCliente");
		Query query = new Query();
		query.addCriteria(Criteria.where("idCliente").is(idCliente));
		List<Chamado> chamados = mongoTemplate.find(query, Chamado.class);
		log.info("[finish] ChamadoInfraRepository - buscaChamadosDoCliente");
		return chamados;
	}

	@Override
	public List<Chamado> buscaChamadosDoTecnico(UUID idTecnico) {
		log.info("[start] ChamadoInfraRepository - buscaChamadosDoTecnico");
		Query query = new Query();
		query.addCriteria(Criteria.where("idTecnico").is(idTecnico));
		List<Chamado> chamados = mongoTemplate.find(query, Chamado.class);
		log.info("[finish] ChamadoInfraRepository - buscaChamadosDoTecnico");
		return chamados;
	}

	@Override
	public void deletaChamado(Chamado chamado) {
		log.info("[start] ChamadoInfraRepository - deletaChamado");
		chamadoSpringDBMongoRepository.delete(chamado);
		log.info("[finish] ChamadoInfraRepository - deletaChamado");
	}

}
