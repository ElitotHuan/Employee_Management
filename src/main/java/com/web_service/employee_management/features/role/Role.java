package com.web_service.employee_management.features.role;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	@Override
	public String toString() {
		return name.name();
	}
}