package com.eurder.eurder.service.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    protected void customerNotFoundException(CustomerNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected void itemNotFoundException(ItemNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    protected void orderNotFoundException(OrderNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(ElementAlreadyRegisteredInDatabaseException.class)
    protected void elementAlreadyRegisteredInDatabaseException(ElementAlreadyRegisteredInDatabaseException exception, HttpServletResponse response) throws IOException {
        response.sendError(FOUND.value(),exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected void unauthorizedException(UnauthorizedException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(),exception.getMessage());
    }

    @ExceptionHandler(UnknownUserException.class)
    protected void unknownUserException(UnknownUserException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(),exception.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected void wrongPasswordException(WrongPasswordException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(),exception.getMessage());
    }
}
