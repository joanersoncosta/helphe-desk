package com.wakanda.credencial.application.service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wakanda.credencial.domain.Credencial;

public interface CredencialMongoSpringRepository extends MongoRepository<Credencial, String> {
	Optional<Credencial> findByUsuario(String Usuario);
}
