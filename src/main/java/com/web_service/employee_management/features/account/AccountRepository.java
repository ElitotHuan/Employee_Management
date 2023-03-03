package com.web_service.employee_management.features.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT new com.web_service.employee_management.features.account.AccountDTO(p.employee.fullName "
            + ", p.password , p.expDate ,p.update_date) from Account p")
    List<AccountDTO> getAccounts();

    Account findByPassword(String password);

    Account findByEmail(String email);

    boolean existsByEmail(String email);

}
