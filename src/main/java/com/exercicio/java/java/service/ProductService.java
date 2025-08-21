package com.exercicio.java.java.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.java.java.dto.ProductDTO;
import com.exercicio.java.java.entity.Product;
import com.exercicio.java.java.repository.RepositoryProduct;

import lombok.Data;

@Service
@Data
public class ProductService {

    @Autowired
    private RepositoryProduct repositoryProduct;

    private ModelMapper modelMapper = new ModelMapper();



    public ProductDTO createProduct(ProductDTO productDTO) {
        Product newProduct = modelMapper.map(productDTO, Product.class);
        Optional<Product> produtoExistente = repositoryProduct.findByNomeProduto(newProduct.getNomeProduto());

        if (produtoExistente.isPresent()) {
            throw new RuntimeException("Produto ja adicionado");
        }

        Product product = repositoryProduct.save(newProduct);
        ProductDTO productDTOConvertido = modelMapper.map(product, ProductDTO.class);
        return productDTOConvertido;

    }

    public List<ProductDTO> getAllProducts() {
        List<Product> list = repositoryProduct.findAll();
        List<ProductDTO> productDTO = list.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTO;
    }

    public ProductDTO getByIdProduct(Long id) {
        Product produtoPorId = repositoryProduct.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ProductDTO productDTO = modelMapper.map(produtoPorId, ProductDTO.class);
        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO newProduct) {
        Product productUpdate = repositoryProduct.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não existe"));

        double valor = productUpdate.getValor();

        productUpdate.setNomeProduto(newProduct.getNomeProduto());
        productUpdate.setDescricao(newProduct.getDescricao());
        productUpdate.setValor(valor);

        repositoryProduct.save(productUpdate);

        ProductDTO productDTO = modelMapper.map(productUpdate, ProductDTO.class);

        return productDTO;

    }

    public ProductDTO deleteProduct(Long id) {
        Product produtoDeletado = repositoryProduct.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com" + id + "não encontrado"));
        repositoryProduct.deleteById(id);
        ProductDTO productDTO = modelMapper.map(produtoDeletado, ProductDTO.class);

        return productDTO;
    }
}
