package com.exercicio.java.java.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.java.java.entity.Product;



public interface RepositoryProduct extends JpaRepository <Product, Long>, ProductNotificationRepository {
    Optional<Product> findByNomeProduto(String nome);
}
