package com.web_service.employee_management.module.employee;

import com.web_service.employee_management.module.department.Department;
import com.web_service.employee_management.module.job.Job;
import com.web_service.employee_management.module.password.Password;
import com.web_service.employee_management.module.salary.Salary;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Table(name = "employee")
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_id", nullable = false)
    private Long id;

    @Column(name = "Name", columnDefinition = "TEXT", nullable = false)
    @NotEmpty
    private String fullName;

    @Column(name = "Birthday", columnDefinition = "Date", nullable = false)
    @NotEmpty
    private LocalDate birthDay;

    @Column(name = "Email", columnDefinition = "TEXT", nullable = false)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "Phone_number", columnDefinition = "Numeric(10)", nullable = false)
    @NotNull
    @Max(11)
    @Min(10)
    private int phoneNumber;

    @Column(name = "Hired_date", columnDefinition = "Date", nullable = false)
    private LocalDate hiredDate;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Salary salary;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Password password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id")
    private Department department;


    @Data
    public static class EmployeeID {
        @NotNull
        private Long id;
    }

    public Employee() {
    }

    public Employee(String fullName, LocalDate birthDay, String email, int phoneNumber, LocalDate hiredDate) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hiredDate = hiredDate;
    }
}
