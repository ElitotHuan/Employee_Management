package com.web_service.employee_management.execptions.customexceptions;

public class ExpiredException extends  RuntimeException{

    public ExpiredException(String message) {
        super(message);
    }
}
