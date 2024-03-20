package com.wakanda.chamado.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wakanda.chamado.domain.Chamado;

public interface ChamadoRepository {

	Chamado salva(Chamado chamado);

	Optional<Chamado> buscaChamadoPorId(UUID idChamado);

	List<Chamado> buscaChamados();

}
