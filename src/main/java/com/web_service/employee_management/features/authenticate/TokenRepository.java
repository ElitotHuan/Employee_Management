package com.web_service.employee_management.features.authenticate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<TokenJWT, Long> {
    @Modifying
    @Query(value = "Update token_info set access_token=:#{#t.accessToken} , create_date=:#{#t.createDate} , exp_date=:#{#t.expDate} , refresh_token=:#{#t.refreshToken} " +
            "where employee_id =:#{#t.account.employee.id}", nativeQuery = true)
    @Transactional
    int updateToken(@Param("t") TokenJWT t);

    TokenJWT findByRefreshToken(String refreshToken);
}
