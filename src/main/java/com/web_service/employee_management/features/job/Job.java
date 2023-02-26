package com.web_service.employee_management.features.job;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.web_service.employee_management.features.employee.Employee;

import java.util.List;

@Table(name = "job")
@Entity
@Data
public class Job {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Long job_Id;

    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;

    @Column(name = "salary_min", nullable = false)
    @NotNull
    @Min(1000)
    private double salary_min;

    @Column(name = "salary_max", nullable = false)
    @Max(999999)
    private double salary_max;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Employee> employee;

}
