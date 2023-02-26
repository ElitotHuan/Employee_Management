package com.web_service.employee_management.features.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDTO {

	private String employee_name;
	private String password;
	private LocalDate expDate;
	private LocalDate update_date;

	public AccountDTO(String employee_name, String password, LocalDate expDate, LocalDate update_date) {
		this.employee_name = employee_name;
		this.password = password;
		this.expDate = expDate;
		this.update_date = update_date;
	}
}
