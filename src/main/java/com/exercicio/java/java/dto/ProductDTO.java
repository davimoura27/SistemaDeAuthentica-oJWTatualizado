package com.exercicio.java.java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {    
    
    @NotBlank
    private String nomeProduto;
    
    @Size(min = 10, max = 100)
    private String descricao;
}
