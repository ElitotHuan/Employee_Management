package com.web_service.employee_management.features.employee;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.web_service.employee_management.features.account.Account;
import com.web_service.employee_management.features.department.Department;
import com.web_service.employee_management.features.job.Job;
import com.web_service.employee_management.features.role.Role;
import com.web_service.employee_management.features.salary.Salary;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
	private Account account;

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

	public Employee(String fullName, LocalDate birthDay, int phoneNumber, LocalDate hiredDate) {
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.hiredDate = hiredDate;
	}
}
