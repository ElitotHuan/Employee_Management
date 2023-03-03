package com.web_service.employee_management.features.authenticate;

import com.web_service.employee_management.features.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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


	@Data
	public static class RequestRefreshToken{
		private String refreshToken;
	}

	@Data
	public static class ResponseRefreshToken{
		private String message;
		private String refreshToken;

		public ResponseRefreshToken(String message, String refreshToken) {
			this.message = message;
			this.refreshToken = refreshToken;
		}
	}

	public TokenJWT() {
		super();
	}

	public TokenJWT(Account account) {
		super();
		this.account = account;
	}

}
