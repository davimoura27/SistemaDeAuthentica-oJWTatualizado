package com.exercicio.java.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exercicio.java.java.dto.UserDTO;
import com.exercicio.java.java.exception.EmailExistenteException;
import com.exercicio.java.java.service.UserRegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserRegisterService userService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {

        try {      
            return ResponseEntity.ok(userService.createUser(userDTO));
                
        } catch (EmailExistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
