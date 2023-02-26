package com.web_service.employee_management.features.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query(value = "SELECT new com.web_service.employee_management.features.salary.SalaryDTO (s.employee.id , s.employee.fullName , s.salary, s.from_date , s.to_date) from Salary s")
    public List<SalaryDTO> getSalaries();
}
