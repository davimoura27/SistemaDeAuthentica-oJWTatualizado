package com.exercicio.java.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String message){
        super(message);
    }
}
