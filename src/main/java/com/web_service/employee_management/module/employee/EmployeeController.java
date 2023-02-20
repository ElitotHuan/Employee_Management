package com.web_service.employee_management.module.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping(value = "/employee/get")
    @ResponseStatus(HttpStatus.OK)
    public Object getEmployee(@RequestParam(required = false) Long id) {
        return service.getAll(id);
    }

    @PostMapping(value = "/employee/add")
    @ResponseStatus(HttpStatus.OK)
    public Object addEmployee(@Valid @RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @PutMapping(value = "/employee/update")
    @ResponseStatus(HttpStatus.OK)
    public Object updateEmployee(@Valid @RequestBody Employee employee) {
        return service.updateEmployee(employee.getId(), employee);
    }

    @DeleteMapping(value = "/employee/delete")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteEmploye(@RequestBody Employee.EmployeeID employeeID) {
        return service.deleteEmployee(employeeID.getId());
    }

}
