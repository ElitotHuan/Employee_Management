package com.web_service.employee_management.execptions.customexceptions;

public class RefreshTokenException extends RuntimeException{

    public RefreshTokenException(String message){
        super(message);
    }

}
