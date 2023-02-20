package com.web_service.employee_management.module.salary;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class SalaryDTO {

    private Long employee_id;

    private String employee_name;

    private double salary;

    private LocalDate from_date;

    private LocalDate to_date;

    public SalaryDTO(Long employee_id, String employee_name, double salary, LocalDate from_date, LocalDate to_date) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.salary = salary;
        this.from_date = from_date;
        this.to_date = to_date;
    }
}
