package com.wakanda.cliente.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wakanda.cliente.domain.Cliente;

public interface ClienteSpringDBMongoRepository extends MongoRepository<Cliente, UUID>{

	Optional<Cliente> findByEmail(String email);

}
