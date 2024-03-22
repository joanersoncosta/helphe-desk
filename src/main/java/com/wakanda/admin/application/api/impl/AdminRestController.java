package com.wakanda.admin.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import com.wakanda.admin.application.api.AdminAPI;
import com.wakanda.admin.application.api.request.AdminRequest;
import com.wakanda.admin.application.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AdminRestController implements AdminAPI {
	private final AdminService adminServicce;

	@Override
	public void criaCredencialAdmin(AdminRequest adminRequest) {
		log.info("[inicia] AdminRestController - criaCredencialAdmin");
		adminServicce.criaCredencialAdmin(adminRequest);
		log.info("[finaliza] AdminRestController - criaCredencialAdmin");
	}

}
