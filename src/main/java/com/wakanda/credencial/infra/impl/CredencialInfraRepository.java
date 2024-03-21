package com.wakanda.credencial.infra.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.wakanda.credencial.application.repository.CredencialRepository;
import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.infra.CredencialMongoSpringRepository;
import com.wakanda.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CredencialInfraRepository implements CredencialRepository {
	private final CredencialMongoSpringRepository credencialMongoRepository;

	@Override
	public void salva(Credencial credencial) {
		log.info("[start] CredencialRepositoryMongoDB - salva");
		credencialMongoRepository.save(credencial);
		log.info("[finish] CredencialRepositoryMongoDB - salva");
	}

	@Override
	public Credencial buscaCredencialPorUsuario(String usuario) {
		log.info("[start] CredencialRepositoryMongoDB - buscaCredencialPorUsuario");
		var credencial = credencialMongoRepository.findByUsuario(usuario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Não existe credencial para o úsuario informado!"));
		log.info("[start] CredencialRepositoryMongoDB - buscaCredencialPorUsuario");
		return credencial;

	}

}
