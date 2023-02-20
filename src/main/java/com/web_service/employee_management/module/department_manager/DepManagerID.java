package com.web_service.employee_management.module.department_manager;

import com.web_service.employee_management.module.department.Department;
import com.web_service.employee_management.module.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class DepManagerID implements Serializable {

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public DepManagerID() {

    }

    @Data
    public static class RequestInfo {
        private Long depId;

        private Long empId;
    }

    public DepManagerID(Department department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }
}
