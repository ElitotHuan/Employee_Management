package com.web_service.employee_management.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web_service.employee_management.features.account.Account;
import com.web_service.employee_management.features.account.AccountRepository;
import com.web_service.employee_management.features.employee.Employee;
import com.web_service.employee_management.features.employee.EmployeeRepository;

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
