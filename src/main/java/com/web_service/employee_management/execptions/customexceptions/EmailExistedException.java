package com.web_service.employee_management.execptions.customexceptions;

public class EmailExistedException extends RuntimeException{
    public EmailExistedException(String message){
        super(message);
    }

}
