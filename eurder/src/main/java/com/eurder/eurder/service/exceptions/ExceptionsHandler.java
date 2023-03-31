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

    protected void orderNotFoundException(OrderNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(),exception.getMessage());
    }
}
