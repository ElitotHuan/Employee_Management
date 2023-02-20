package com.web_service.employee_management.module.salary;

import com.web_service.employee_management.module.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "salaries")
@Entity
@Data
public class Salary {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @MapsId
    private Employee employee;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "from_date", columnDefinition = "Date", nullable = false)
    private LocalDate from_date;

    @Column(name = "to_date", columnDefinition = "Date", nullable = false)
    private LocalDate to_date;

    public Salary() {
    }

    public Salary(Employee employee, double salary, LocalDate from_date, LocalDate to_date) {
        this.employee = employee;
        this.salary = salary;
        this.from_date = from_date;
        this.to_date = to_date;
    }
}
