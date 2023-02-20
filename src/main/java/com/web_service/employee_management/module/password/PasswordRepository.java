package com.web_service.employee_management.module.password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query(value = "SELECT new com.web_service.employee_management.module.password.PasswordDTO(p.employee.fullName , p.password , p.expDate ,p.update_date) from Password p")
    public List<PasswordDTO> getPasswords();

}
