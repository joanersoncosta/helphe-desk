package com.wakanda.chamado.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wakanda.chamado.domain.Chamado;
import com.wakanda.chamado.domain.enuns.Prioridade;
import com.wakanda.chamado.domain.enuns.StatusChamado;

public interface ChamadoRepository {

	Chamado salva(Chamado chamado);

	Optional<Chamado> buscaChamadoPorId(UUID idChamado);

	List<Chamado> buscaChamados();

	List<Chamado> buscaChamadosPorPrioridade(Prioridade prioridade);

	List<Chamado> buscaChamadosDoTecnicoPorPrioridade(UUID idTecnico, Prioridade prioridade);

	List<Chamado> buscaChamadosDoClientePorPrioridade(UUID idCliente, Prioridade prioridade);

	List<Chamado> buscaChamadosPorStatus(StatusChamado status);

	List<Chamado> buscaChamadosDoTecnicoPorStatus(UUID idTecnico, StatusChamado status);

	List<Chamado> buscaChamadosDoClientePorStatus(UUID idCliente, StatusChamado status);

	List<Chamado> buscaChamadosDoCliente(UUID idCliente);

	List<Chamado> buscaChamadosDoTecnico(UUID idTecnico);
	
	void deletaChamado(Chamado chamado);

}
