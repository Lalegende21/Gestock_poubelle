package com.gestock.services;

import com.gestock.entites.Product;
import com.gestock.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    //Methide pour recuperer tous les produits
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return productRepository.findAll(pageable);
    }

    public List<Product> getAllProducts() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return this.productRepository.findAll(sort);
    }

    //Methode pour enregistrer un produit
    public Product saveProduct(Product product) {
        this.productRepository.save(product);
        return product;
    }
}
