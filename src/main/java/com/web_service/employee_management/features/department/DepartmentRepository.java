package com.web_service.employee_management.features.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsBydepartmentName(String name);

    @Query(value = "SELECT new com.web_service.employee_management.features.department.DepartmentDTO(d.departmentName , d.numberOfEmployees) " +
            "from Department d")
    List<DepartmentDTO> getDepartments();
}
