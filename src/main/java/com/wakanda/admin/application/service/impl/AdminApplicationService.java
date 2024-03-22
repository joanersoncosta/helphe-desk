package com.wakanda.admin.application.service.impl;

import org.springframework.stereotype.Service;

import com.wakanda.admin.application.api.request.AdminRequest;
import com.wakanda.admin.application.service.AdminService;
import com.wakanda.credencial.application.service.CredencialService;
import com.wakanda.credencial.domain.perfis.CredencialAdmin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminApplicationService implements AdminService {
	private final CredencialService credencialService;

	@Override
	public void criaCredencialAdmin(AdminRequest adminRequest) {
		log.info("[inicia] AdminApplicationService - criaCredencialAdmin");
		credencialService.criaNovaCredencial(new CredencialAdmin(adminRequest));
		log.info("[finaliza] AdminApplicationService - criaCredencialAdmin");
	}

}
