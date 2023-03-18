package com.web_service.employee_management.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

		public String convertToJson() throws JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			return mapper.writeValueAsString(this);
		}
	}
}
