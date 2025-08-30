package com.exercicio.java.java.exception;



public class EmailExistenteException extends RuntimeException {

    public EmailExistenteException(){
        super("Email ja cadastrado!");
    }
    
    public EmailExistenteException(String message) {
        super(message);
    }
}
