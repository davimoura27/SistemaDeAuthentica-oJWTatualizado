package com.exercicio.java.java.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ResponseUserDTO {

    private Long id;

    private String userName;

    private String telephone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;

    private String email;
}
