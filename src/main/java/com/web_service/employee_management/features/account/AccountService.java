package com.web_service.employee_management.features.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.web_service.employee_management.security.UserDetailsImpl;

@Service
public class AccountService {

	@Autowired
	AccountRepository passwordRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	public Object getPasswords() {
		return passwordRepository.getPasswords();
	}

	public Account signin(Account.LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return passwordRepository.findByPassword(userDetails.getPassword());

	}
	
}
