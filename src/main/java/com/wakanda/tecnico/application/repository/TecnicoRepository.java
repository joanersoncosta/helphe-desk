package com.wakanda.tecnico.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.wakanda.tecnico.domain.Tecnico;

public interface TecnicoRepository {

	Tecnico salva(Tecnico tecnico);

	Optional<Tecnico> buscaTecnicoPorId(UUID idTecnico);

}
