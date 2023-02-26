package com.web_service.employee_management.features.authenticate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web_service.employee_management.features.account.Account;

import lombok.Data;

@Table(name = "token_info")
@Entity
@Data
public class TokenJWT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "access_token", columnDefinition = "TEXT", nullable = false)
	private String accessToken;

	@Column(name = "refresh_token", columnDefinition = "TEXT", nullable = false)
	private String refreshToken;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "exp_date", nullable = false)
	private Date expDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Account account;

	public TokenJWT(String accessToken, String refreshToken, Date createDate, Date expDate) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.createDate = createDate;
		this.expDate = expDate;
	}

	public TokenJWT() {
		super();
	}

	public TokenJWT(Account account) {
		super();
		this.account = account;
	}

}
