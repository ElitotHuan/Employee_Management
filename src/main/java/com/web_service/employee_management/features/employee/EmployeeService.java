package com.web_service.employee_management.features.employee;

import com.web_service.employee_management.execptions.customexceptions.EmailExistedException;
import com.web_service.employee_management.features.account.Account;
import com.web_service.employee_management.features.account.AccountRepository;
import com.web_service.employee_management.features.department.Department;
import com.web_service.employee_management.features.department.DepartmentRepository;
import com.web_service.employee_management.features.role.ERole;
import com.web_service.employee_management.features.role.Role;
import com.web_service.employee_management.features.role.RoleRepository;
import com.web_service.employee_management.features.salary.Salary;
import com.web_service.employee_management.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {
	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	PasswordEncoder encoder;

	public Object getAll(Long id) {
		logger.info("Loading...");
		if (id == null) {
			return repository.getEmployees();
		} else {
			Employee employee = repository.findById(id).get();
			return new EmployeeDTO(employee.getId(), employee.getFullName(), employee.getBirthDay(),
					employee.getPhoneNumber(), employee.getHiredDate(), employee.getJob().getTitle(),
					employee.getSalary().getSalary());
		}
	}

	public Object addEmployee(Employee employeeInfo) {
		logger.info("Initialize adding employee function....");
		if (accountRepository.existsByEmail(employeeInfo.getAccount().getEmail())) {
			throw new EmailExistedException("Email already taken");
		} else {
			Employee newEmployee = new Employee(employeeInfo.getFullName(), employeeInfo.getBirthDay(),
					employeeInfo.getPhoneNumber(), LocalDate.now());
			Department d = departmentRepository.findById(employeeInfo.getDepartment().getId()).get();

			// Set Salary
			newEmployee.setSalary(new Salary(newEmployee, employeeInfo.getSalary().getSalary(), LocalDate.now(),
					LocalDate.now().plusYears(3)));

			// Set Account
			newEmployee.setAccount(new Account(newEmployee, employeeInfo.getAccount().getEmail(),
					encoder.encode(employeeInfo.getAccount().getPassword()), LocalDate.now().plusYears(3), null));

			// Set job and department
			newEmployee.setJob(employeeInfo.getJob());
			newEmployee.setDepartment(d);

			// Set roles
			Set<Role> roles = new HashSet<>();
			employeeInfo.getAccount().getRoles().forEach(role -> {
				switch (role.getName().name()) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
			newEmployee.getAccount().setRoles(roles);

			// Update department employees
			d.setNumberOfEmployees(d.getNumberOfEmployees() + 1);

			// Save to database
			repository.save(newEmployee);
			departmentRepository.save(d);
			return new Response.Success("Employee has been added and registered sucessfully");
		}
	}

	public Object updateEmployee(Long id, Employee updateInfo) {
		if (accountRepository.existsByEmail(updateInfo.getAccount().getEmail())) {
			throw new EmailExistedException("Email already taken");
		} else {
			Employee original = repository.getReferenceById(id);

			if (original.getDepartment().getId() != updateInfo.getDepartment().getId()) {
				original = new Employee(updateInfo.getFullName(), updateInfo.getBirthDay(), updateInfo.getPhoneNumber(),
						updateInfo.getHiredDate());
				Department newDepartment = updateInfo.getDepartment();
				Department oldDepartment = original.getDepartment();

				original.setSalary(updateInfo.getSalary());
				original.setJob(updateInfo.getJob());
				original.setDepartment(newDepartment);

				newDepartment.setNumberOfEmployees(newDepartment.getNumberOfEmployees() + 1);
				oldDepartment.setNumberOfEmployees(oldDepartment.getNumberOfEmployees() - 1);

				repository.save(original);
				departmentRepository.save(newDepartment);
				departmentRepository.save(oldDepartment);
				return new Response.Success("Employee information has been updated sucessfully");
			}

			original = new Employee(updateInfo.getFullName(), updateInfo.getBirthDay(), updateInfo.getPhoneNumber(),
					updateInfo.getHiredDate());
			original.setSalary(updateInfo.getSalary());
			original.setJob(updateInfo.getJob());
			original.setDepartment(updateInfo.getDepartment());
			repository.save(original);
			return new Response.Success("Employee information has been updated sucessfully");
		}
	}

	public Object deleteEmployee(Long id) {
		Employee e = repository.getReferenceById(id);
		Department d = departmentRepository.findById(e.getDepartment().getId()).get();
		d.setNumberOfEmployees(d.getNumberOfEmployees() - 1);

		repository.delete(e);
		departmentRepository.save(d);
		return new Response.Success("Remove sucessfully");
	}

}
