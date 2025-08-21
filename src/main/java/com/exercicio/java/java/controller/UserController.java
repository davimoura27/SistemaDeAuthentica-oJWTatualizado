package com.exercicio.java.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exercicio.java.java.dto.ResponseUserDTO;
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
            ResponseUserDTO registerUser = userService.createUser(userDTO);

            return ResponseEntity.ok(registerUser);

        } catch (EmailExistenteException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email ja existente!");
        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Senha de confirmação incorreta!");
        }

    }

}
