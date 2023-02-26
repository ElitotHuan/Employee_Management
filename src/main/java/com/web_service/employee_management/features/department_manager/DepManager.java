package com.web_service.employee_management.features.department_manager;

import lombok.Data;

import javax.persistence.*;

import com.web_service.employee_management.features.department.Department;
import com.web_service.employee_management.features.employee.Employee;

import java.time.LocalDate;

@Table(name = "Dep_Manager")
@Entity
@Data
public class DepManager {

    @EmbeddedId
    private DepManagerID id;

    @Column(name = "from_date", columnDefinition = "Date" , nullable = false)
    private LocalDate from_date;

    @Column(name = "to_date", columnDefinition = "Date" , nullable = false)
    private LocalDate to_date;

    public DepManager(DepManagerID id, LocalDate from_date, LocalDate to_date) {
        this.id = id;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public DepManager() {

    }
}
