package com.web_service.employee_management.features.department_manager;

import lombok.Data;

import javax.persistence.*;

import com.web_service.employee_management.features.department.Department;
import com.web_service.employee_management.features.employee.Employee;

import java.io.Serializable;

@Embeddable
@Data
public class DepManagerID implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
