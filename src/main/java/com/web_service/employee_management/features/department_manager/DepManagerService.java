package com.web_service.employee_management.features.department_manager;

import com.web_service.employee_management.features.department.Department;
import com.web_service.employee_management.features.department.DepartmentRepository;
import com.web_service.employee_management.features.employee.Employee;
import com.web_service.employee_management.features.employee.EmployeeRepository;
import com.web_service.employee_management.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepManagerService {

	@Autowired
	DepManagerRepository repository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public Object getManagers() {
		return repository.getManagers();
	}

	public Object addManager(DepManagerID.RequestInfo requestInfo) {
		Employee employee = employeeRepository.getReferenceById(requestInfo.getEmpId());
		Department department = departmentRepository.getReferenceById((requestInfo.getDepId()));

		repository.save(
				new DepManager(new DepManagerID(department, employee), LocalDate.now(), LocalDate.now().plusYears(3)));
		return new Response.Success("A new manager has been added");
	}
}
