package com.web_service.employee_management.features.department;

import com.web_service.employee_management.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    public Object getAllDep() {
        return repository.getDepartments();
    }

    public Object addDepartment(Department department) {
        if (repository.existsBydepartmentName(department.getDepartmentName())) {
            return new String("Department already existed");
        } else {
            repository.save(department);
            return new Response.Success("Department has been added sucessfully");
        }
    }


}
