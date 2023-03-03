package com.web_service.employee_management.execptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.web_service.employee_management.execptions.customexceptions.EmailExistedException;
import com.web_service.employee_management.execptions.customexceptions.ExpiredException;
import com.web_service.employee_management.execptions.customexceptions.RefreshTokenException;
import com.web_service.employee_management.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExecptionsHandler extends ResponseEntityExceptionHandler {

    //Rest Exceptions
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        Throwable throwable = ex.getCause();
        if (throwable instanceof InvalidFormatException) {
            logger.error(throwable.getMessage());
            return buildResponseEntity(new Response.Error("Invalid format", HttpStatus.BAD_REQUEST.value()));
        }
        return buildResponseEntity(new Response.Error("Missing Request Body", status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> listErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getField() + " " + fieldError.getDefaultMessage();
            listErrors.add(errorMessage);
        }
        logger.warn(listErrors.toString());
        return buildResponseEntity(new Response.Error("There is something wrong with input values", status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error("Missing header", status.value()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.warn(ex.getMessage());
        return buildResponseEntity(new Response.Error("Can't find user", HttpStatus.NO_CONTENT.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(EmailExistedException.class)
    protected ResponseEntity<Object> handleUserExistedException(EmailExistedException ex) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error("Email already existed", HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(ExpiredException.class)
    protected ResponseEntity<Object> handleUserExistedException(ExpiredException ex) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(AuthenticationException.class )
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        logger.error(ex.getMessage());
        return buildResponseEntity(new Response.Error("Authentication failed at controller advice", HttpStatus.UNAUTHORIZED.value()));
    }

    //JWT exceptions
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleMalformedJwtException(MalformedJwtException ex) {
        logger.error(ex.getMessage());
        return new Response.Error("Access denied!", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleUnsupportedJwtException(UnsupportedJwtException ex) {
        logger.error(ex.getMessage());
        return new Response.Error("Access denied!", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleSignatureException(SignatureException ex) {
        logger.error(ex.getMessage());
        return new Response.Error("Access denied!", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleExpiredJwtException(ExpiredJwtException ex) {
        logger.error("JWT expired at " + ex.getClaims().getExpiration());
        return new Response.Error("Access denied!", HttpStatus.UNAUTHORIZED.value());
    }

//    @ExceptionHandler(RolesAuthorizationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    protected ErrorRespone handleRolesAuthorizationException(RolesAuthorizationException ex) {
//        logger.error(ex.getMessage());
//        return new ErrorRespone("Forbidden Access!", HttpStatus.FORBIDDEN.value());
//    }
//
    @ExceptionHandler(RefreshTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleRefreshTokenException(RefreshTokenException ex) {
        logger.error(ex.getMessage());
        return new Response.Error("Unauthorized!", HttpStatus.FORBIDDEN.value());
    }

    private ResponseEntity<Object> buildResponseEntity(Response.Error errorRespone) {
        return new ResponseEntity<>(errorRespone, HttpStatus.valueOf(errorRespone.getCode()));
    }


}
