package com.wakanda.credencial.application.service;

import com.wakanda.credencial.domain.Credencial;
import com.wakanda.credencial.domain.perfis.CredencialAdmin;
import com.wakanda.credencial.domain.perfis.CredencialCliente;
import com.wakanda.credencial.domain.perfis.CredencialTecnico;

public interface CredencialService {
	
	void criaNovaCredencial(CredencialCliente cliente);

	void criaNovaCredencial(CredencialTecnico tecnico);

	void criaNovaCredencial(CredencialAdmin admin);

	Credencial buscaCredencialPorUsuario(String usuario);

}
