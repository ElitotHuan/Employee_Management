package com.web_service.employee_management.module.department_manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepManagerRepository  extends JpaRepository<DepManager, DepManagerID> {

    @Query(value = "select new com.web_service.employee_management.module.department_manager.DepManagerDTO(dm.id.department.departmentName , dm.id.employee.fullName) " +
            "from DepManager dm ")
    List<DepManagerDTO> getManagers();
}
