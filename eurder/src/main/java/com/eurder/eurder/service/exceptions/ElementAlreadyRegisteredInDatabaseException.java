package com.eurder.eurder.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ElementAlreadyRegisteredInDatabaseException extends RuntimeException{
    public ElementAlreadyRegisteredInDatabaseException(String message) {
        super(message);
    }
}
