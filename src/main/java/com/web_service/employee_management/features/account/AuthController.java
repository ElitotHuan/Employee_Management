package com.web_service.employee_management.features.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.employee_management.features.authenticate.TokenJWT;
import com.web_service.employee_management.features.authenticate.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private TokenService service;

	@Autowired
	private AccountService passwordService;

	@PostMapping(value = "/login")
	@ResponseStatus(HttpStatus.OK)
	public Object signin(@Valid @RequestBody Account.LoginRequest loginRequest) {
		Account p = passwordService.signin(loginRequest);
		TokenJWT t = service.createToken(new TokenJWT(p));
		return new Account.LoginResponse("login success", t.getAccessToken(), t.getRefreshToken());
	}
}
