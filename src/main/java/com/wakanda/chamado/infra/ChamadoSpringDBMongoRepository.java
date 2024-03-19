package com.wakanda.chamado.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wakanda.chamado.domain.Chamado;

public interface ChamadoSpringDBMongoRepository extends MongoRepository<Chamado, UUID>{

}
