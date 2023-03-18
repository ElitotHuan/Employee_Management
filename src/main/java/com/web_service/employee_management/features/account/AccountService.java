package com.web_service.employee_management.features.account;


import com.web_service.employee_management.execptions.customexceptions.ExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public Object getAccounts() {
        return accountRepository.getAccounts();
    }

    public Account signin(Account.LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user1  = (User) authentication.getPrincipal();
        Account account = accountRepository.findByEmail(user1.getUsername());

        if (!account.getExpDate().isBefore(LocalDate.now())) {
            return account;
        } else {
            throw new ExpiredException("Account has been expired, please contact your admin");
        }
    }
}
