package com.web_service.employee_management.features.department_manager;

import lombok.Data;

@Data
public class DepManagerDTO {

    private String dep_name;

    private String employee_name;

    public DepManagerDTO(String dep_name, String employee_name) {
        this.dep_name = dep_name;
        this.employee_name = employee_name;
    }
}
