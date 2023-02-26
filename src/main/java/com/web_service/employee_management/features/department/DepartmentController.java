package com.web_service.employee_management.features.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
    @Autowired
    private DepartmentService service;

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public Object getDepartment() {
        return service.getAllDep();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public Object addDepartment(@Valid @RequestBody Department department) {
        return service.addDepartment(department);
    }
}
