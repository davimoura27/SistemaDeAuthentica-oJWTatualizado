package com.exercicio.java.java.repository;

import com.exercicio.java.java.entity.Product;

public class ProductNotificationRepositoryImpl implements ProductNotificationRepository {

    @Override
    public void notifyProduct(Product product) {
        System.out.println(product.getNomeProduto() + " cadastrado com sucesso!");
    }

}
