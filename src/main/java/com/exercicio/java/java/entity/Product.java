package com.exercicio.java.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Product {
    
    @Id
    @GeneratedValue
    @Column(name = "ids", unique = true)
    private Long id;
    
    @Column(name = "Product", nullable = false, length = 20)
    String nomeProduto;
    
    @Column(name = "Description", length = 500)
    String descricao;
    
    @Column(name = "PriceR$", nullable = false)
    double valor;

    
}

