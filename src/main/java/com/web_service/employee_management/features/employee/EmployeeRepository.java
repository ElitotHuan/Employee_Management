package com.web_service.employee_management.features.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select new com.web_service.employee_management.features.employee.EmployeeDTO (e.id , e.fullName , e.birthDay "
			+ ", e.phoneNumber , e.hiredDate , e.job.title , e.salary.salary) from Employee e")
	List<EmployeeDTO> getEmployees();


}
