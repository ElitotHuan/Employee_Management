package com.web_service.employee_management.module.salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository repository;


    public Object getSalary() {
        return repository.getSalaries();
    }

}
