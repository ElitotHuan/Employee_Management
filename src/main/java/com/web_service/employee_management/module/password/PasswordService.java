package com.web_service.employee_management.module.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    public Object getPasswords() {
        return passwordRepository.getPasswords();
    }

}
