package com.web_service.employee_management.module.department_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class DepManagerController {

    @Autowired
    private DepManagerService service;

    @GetMapping(value = "/DepManager/get")
    @ResponseStatus(HttpStatus.OK)
    public Object getManagers() {
        return service.getManagers();
    }

    @PostMapping(value = "/DepManager/add")
    @ResponseStatus(HttpStatus.OK)
    public Object addManagers(@RequestBody DepManagerID.RequestInfo requestInfo) {
        return service.addManager(requestInfo);
    }
}
