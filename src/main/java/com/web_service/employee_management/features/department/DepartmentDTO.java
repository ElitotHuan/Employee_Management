package com.web_service.employee_management.features.department;

import lombok.Data;

@Data
public class DepartmentDTO {

    private String depName;

    private int numberOfEmployees;

    public DepartmentDTO(String depName, int numberOfEmployees) {
        this.depName = depName;
        this.numberOfEmployees = numberOfEmployees;
    }
}
