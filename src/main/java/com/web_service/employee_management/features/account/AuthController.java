package com.web_service.employee_management.features.account;

import com.web_service.employee_management.features.authenticate.TokenJWT;
import com.web_service.employee_management.features.authenticate.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private TokenService service;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/admin/accounts/get")
    @ResponseStatus(HttpStatus.OK)
    public Object get() {
        return accountService.getAccounts();
    }

    @PostMapping(value = "/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public Object signin(@Valid @RequestBody Account.LoginRequest loginRequest) {
        Account p = accountService.signin(loginRequest);
        TokenJWT t = service.createToken(new TokenJWT(p));
        return new Account.LoginResponse("login success", t.getAccessToken(), t.getRefreshToken());
    }

    @PostMapping(value = "/auth/refreshToken")
    @ResponseStatus(HttpStatus.OK)
    public Object refreshToken(@Valid @RequestBody TokenJWT.RequestRefreshToken requestRefreshToken) {
        TokenJWT t = service.updateToken(requestRefreshToken.getRefreshToken());
        return new TokenJWT.ResponseRefreshToken("Assaigned new Access token", t.getRefreshToken());
    }

}
