package com.wakanda.tecnico.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wakanda.tecnico.domain.Tecnico;

public interface TecnicoSpringDBMongoRepository extends MongoRepository<Tecnico, UUID>{

	Optional<Tecnico> findByEmail(String email);

}
