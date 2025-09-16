package com.exercicio.java.java.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
   
    @NotBlank(message = "O campo email é obrigatorio!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatorio!")
    private String password;
}
