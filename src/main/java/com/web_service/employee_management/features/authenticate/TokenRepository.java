package com.web_service.employee_management.features.authenticate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<TokenJWT, Long> {
    @Query(value = "Update token_info t set t.access_token=:#{#t.access_token} , t.create_date=:#{#t.create_date} , t.exp_date=:#{#t.exp_date} , t.refresh_token=:#{#t.refresh_token} " +
            "where t.employee_id =:#{#t.account.employee.id}", nativeQuery = true)
    @Transactional
    public void updateToken(TokenJWT t);
}
