package com.exercicio.java.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exercicio.java.java.dto.LoginDTO;
import com.exercicio.java.java.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

   @Autowired
   private LoginService loginService;

   @PostMapping("/login")
   public ResponseEntity<?> authController(@Valid @RequestBody LoginDTO loginDTO) {
      try {         
         return ResponseEntity.ok().body(loginService.authService(loginDTO));

      } catch (BadCredentialsException e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais incorretas ou usuario não registrado!");

      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidro");
      }
   }
}