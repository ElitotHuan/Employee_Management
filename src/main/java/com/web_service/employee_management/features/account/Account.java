package com.web_service.employee_management.features.account;

import com.web_service.employee_management.features.authenticate.TokenJWT;
import com.web_service.employee_management.features.employee.Employee;
import com.web_service.employee_management.features.role.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "account")
@Entity
@Data
public class Account {
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	@MapsId
	private Employee employee;

	@Column(name = "email", columnDefinition = "TEXT", nullable = false)
	@NotEmpty
	@Email
	private String email;

	@Column(name = "password", columnDefinition = "TEXT", nullable = false)
	private String password;

	@Column(name = "expire_date", columnDefinition = "Date", nullable = false)
	private LocalDate expDate;

	@Column(name = "update_date", columnDefinition = "Date")
	private LocalDate update_date;

	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TokenJWT token;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Data
	public static class LoginRequest {
		private String email;

		private String password;
	}

	@Data
	public static class LoginResponse {
		private String message;

		private String accessToken;

		private String refreshToken;

		public LoginResponse(String message, String accessToken, String refreshToken) {
			super();
			this.message = message;
			this.accessToken = accessToken;
			this.refreshToken = refreshToken;
		}
	}

	public Account(Employee employee, String email, String password, LocalDate expDate, LocalDate update_date) {
		this.employee = employee;
		this.email = email;
		this.password = password;
		this.expDate = expDate;
		this.update_date = update_date;
	}

	public Account(String email, String password, LocalDate expDate, LocalDate update_date, Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.expDate = expDate;
		this.update_date = update_date;
		this.roles = roles;
	}

	public Account() {

	}
}
