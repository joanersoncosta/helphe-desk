package com.wakanda.credencial.application.service;

import com.wakanda.credencial.domain.CredencialCliente;
import com.wakanda.credencial.domain.CredencialTecnico;

public interface CredencialService {
	
	void criaNovaCredencial(CredencialCliente cliente);

	void criaNovaCredencial(CredencialTecnico tecnico);

}
