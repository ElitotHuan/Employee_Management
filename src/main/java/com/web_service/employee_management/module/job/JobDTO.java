package com.web_service.employee_management.module.job;

import lombok.Data;

@Data
public class JobDTO {

    private Long id;

    private String title;

    private double salary_min;

    private double salary_max;

    public JobDTO(Long id, String title, double salary_min, double salary_max) {
        this.id = id;
        this.title = title;
        this.salary_min = salary_min;
        this.salary_max = salary_max;
    }
}
