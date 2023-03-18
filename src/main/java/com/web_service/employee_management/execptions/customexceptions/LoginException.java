package com.web_service.employee_management.execptions.customexceptions;

public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}
