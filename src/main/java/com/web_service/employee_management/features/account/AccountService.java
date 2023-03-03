package com.web_service.employee_management.features.account;


import com.web_service.employee_management.execptions.customexceptions.ExpiredException;
import com.web_service.employee_management.security.userdetails.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Account account = accountRepository.findByEmail(userDetails.getUsername());

		if (!account.getExpDate().isBefore(LocalDate.now())) {
			return account;
		} else {
			throw new ExpiredException("Account has been expired, please contact your admin");
		}
	}
	
}
