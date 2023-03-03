package com.web_service.employee_management.security.userdetails;

import com.web_service.employee_management.features.account.Account;
import com.web_service.employee_management.features.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account user = repository.findByEmail(username);
		return UserDetailsImpl.build(user);
	}
}
