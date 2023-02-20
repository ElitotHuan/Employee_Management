package com.web_service.employee_management.module.department_manager;

import com.web_service.employee_management.module.department.Department;
import com.web_service.employee_management.module.employee.Employee;
import lombok.Data;

import javax.persistence.*;
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
