package com.gestock.controlleurs;

import com.gestock.entites.Product;
import com.gestock.responses.ProductResponseRequest;
import com.gestock.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private ProductService productService;


    @PostMapping(path = "/save")
    public ResponseEntity<?> createProduct(@RequestBody @Valid Product product) {
        try {
            Product productToSave = this.productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productToSave);
        }catch (Exception e) {
            System.out.println(e);
            ProductResponseRequest productResponseRequest = new ProductResponseRequest(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productResponseRequest);
        }
    }

    @GetMapping(path = "/get-all-products")
    public ResponseEntity<Page<Product>> getProductPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Product> products = productService.getAllProducts(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getALlProduct() {
        List<Product> products = this.productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
