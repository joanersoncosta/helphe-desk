package com.wakanda.admin.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wakanda.admin.application.api.request.AdminRequest;

@RestController
@RequestMapping("/public/admin")
public interface AdminAPI {

	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void criaCredencialAdmin(@RequestBody @Valid AdminRequest adminRequest);

}
