package com.wakanda.credencial.application.repository;

import com.wakanda.credencial.domain.Credencial;

public interface CredencialRepository {

	void salva(Credencial novaCredencial);

	Credencial buscaCredencialPorUsuario(String usuario);

	void deletaCredencial(Credencial credencial);

}
