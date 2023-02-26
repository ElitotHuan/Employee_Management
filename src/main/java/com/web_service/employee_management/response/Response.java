package com.web_service.employee_management.response;

import lombok.Data;

public class Response {

	@Data
	public static class Success {
		private String message;

		public Success(String message) {
			this.message = message;
		}
	}

	@Data
	public static class Error {
		private String message;
		private int code;

		public Error(String message, int code) {
			this.message = message;
			this.code = code;
		}
	}
}
