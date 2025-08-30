package com.exercicio.java.java.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<String> handleResource(EmailExistenteException emailExistenteException){
        return new ResponseEntity<>(emailExistenteException.getMessage(),
         HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidateErrors(MethodArgumentNotValidException errosValid){
        Map<String,String> errors = new HashMap<>();

        errosValid.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserExistent(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Erro","Usuario não registrado"));
    }  
}
