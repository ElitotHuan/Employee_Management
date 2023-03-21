package com.web_service.employee_management.security.userdetails;

import com.web_service.employee_management.features.account.Account;
import com.web_service.employee_management.features.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Account user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Set<GrantedAuthority> authorities = user
                    .getRoles()
                    .stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toSet());
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
    }
}
