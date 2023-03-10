package com.web_service.employee_management.features.department;

import lombok.Data;

import javax.persistence.*;

import com.web_service.employee_management.features.employee.Employee;

import java.util.List;

@Table(name = "department")
@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Dep_name", columnDefinition = "TEXT", nullable = false)
    private String departmentName;

    @Column(name = "Number_Employees" , nullable = false)
    private int numberOfEmployees;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Employee> employeeList;
}
