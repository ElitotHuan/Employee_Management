package com.web_service.employee_management.features.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {

	private Long id;

	private String fullName;

	private LocalDate birthDay;

	private int phoneNumber;

	private LocalDate hiredDate;

	private String title;

	private double salary;

	public EmployeeDTO(Long id, String fullName, LocalDate birthDay, int phoneNumber, LocalDate hiredDate, String title,
			double salary) {
		this.id = id;
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.hiredDate = hiredDate;
		this.title = title;
		this.salary = salary;
	}
}
