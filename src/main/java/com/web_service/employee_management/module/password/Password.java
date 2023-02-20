package com.web_service.employee_management.module.password;

import com.web_service.employee_management.module.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "password")
@Entity
@Data
public class Password {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @MapsId
    private Employee employee;
    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;
    @Column(name = "expire_date", columnDefinition = "Date", nullable = false)
    private LocalDate expDate;
    @Column(name = "update_date", columnDefinition = "Date")
    private LocalDate update_date;

    public Password(Employee employee, String password, LocalDate expDate, LocalDate update_date) {
        this.employee = employee;
        this.password = password;
        this.expDate = expDate;
        this.update_date = update_date;
    }

    public Password() {

    }
}
