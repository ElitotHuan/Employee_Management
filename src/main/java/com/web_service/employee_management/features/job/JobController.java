package com.web_service.employee_management.features.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/job")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping(value = "/get")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Object getJobs() {
        return service.getAllJob();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public Object addJob(@Valid @RequestBody Job job) {
        return service.addJob(job);
    }
}
