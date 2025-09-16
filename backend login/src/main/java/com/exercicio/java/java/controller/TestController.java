package com.exercicio.java.java.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exercicio.java.java.service.UserRegisterService;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public UserRegisterService userService;

    @GetMapping("/register")
    public ResponseEntity<?> getAuthenticationUserToken(Authentication authentication){        
          String email = authentication.getName();        
          return ResponseEntity.ok(Map.of("usuario", email,"mensagem","Token valido. Requisição criada!"));        
    }
}
