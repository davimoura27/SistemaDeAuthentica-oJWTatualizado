package com.exercicio.java.java.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "O campo userName é obrigatorio!")
    private String userName;

    @NotBlank(message = "O campo telephone é obrigatorio!")
    @Pattern(
        regexp = "^\\d{11}$", 
        message = "o telefone deve conter 11 digitos numericos incluindo DDD"
    )
    private String telephone;

    @NotNull(message = "A data é obrigatoria")
    private LocalDate dateBirth;

    @Email(message = "Formato de email invalido!")
    @NotBlank(message = "O campo email é obrigatorio!")
    private String email;

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}$",
        message = "A senha deve conter pelo menos 1 numero 1 letra 1 caracter 8 digitos"
    )
    private String password;
}
