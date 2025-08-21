package com.exercicio.java.java.controller;

import org.springframework.web.bind.annotation.RestController;

import com.exercicio.java.java.dto.ProductDTO;

import com.exercicio.java.java.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private ProductService productService;

  @PostMapping("/create")
  public ResponseEntity<ProductDTO> createProducts(@Validated @RequestBody ProductDTO newProduct) {
    ProductDTO product = productService.createProduct(newProduct);

    return ResponseEntity.status(201).body(product);

  }

  @GetMapping("/list/{id}")
  public ResponseEntity<ProductDTO> findByIdProduct(@PathVariable Long id) {
    ProductDTO product = productService.getByIdProduct(id);
    return ResponseEntity.status(200).body(product);
  }

  @GetMapping("/list")
  public ResponseEntity<List<ProductDTO>> getProduct() {
    List<ProductDTO> list = productService.getAllProducts();
    return ResponseEntity.status(200).body(list);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Validated @RequestBody ProductDTO product) {
    ProductDTO produtoAtualizado = productService.updateProduct(id, product);
    return ResponseEntity.status(200).body(produtoAtualizado);

  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<Map<String, String>> deletar(@PathVariable Long id) {
    ProductDTO produto = productService.deleteProduct(id);

    Map<String, String> msg = new HashMap<String, String>();
    msg.put("Produto", produto.getNomeProduto() + "  Status: Deletado com sucesso");

    return ResponseEntity.ok(msg);
  }
}
