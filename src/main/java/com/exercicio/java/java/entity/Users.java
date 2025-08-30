package com.exercicio.java.java.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Users {

   @Id
   @GeneratedValue
   @Column(unique = true)
   private Long id;

   @Column
   @NotBlank(message = "O campo nome é obrigatorio!")
   public String userName;

   @NotNull
   @Pattern(regexp = "^\\d{11}$", message = "o telefone deve conter 11 digitos numericos incluindo DDD")
   private String telephone;

   @NotNull
   private LocalDate dateBirth;


   @Column
   @Email(message = "Formato de email invalido!")
   @NotNull(message = "O campo email é obrigatorio!")
   private String email;


   @Column
   @NotBlank
   @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}$",
    message = "A senha deve conter pelo menos, 1 numero, 1 letra, 1 caracter e 8 digitos")
   private String password;

}
