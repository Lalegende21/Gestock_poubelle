package com.gestock.controlleurs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestock.dto.Message;
import com.gestock.dto.ProductDto;
import com.gestock.entites.Image;
import com.gestock.entites.Product;
import com.gestock.responses.ProductResponseRequest;
import com.gestock.services.ImageService;
import com.gestock.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = "/get-all-products")
    public ResponseEntity<Page<Product>> getProductPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Product> products = productService.getAllProducts(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @PostMapping("/save")
    public ResponseEntity<?> addStudentHandler(@RequestPart MultipartFile file,
                                               @RequestPart String productDto
    ) throws IOException {

        ProductDto dto = convertToProductDtoj(productDto);

        productService.saveProduct(dto, file);
        return  new ResponseEntity<>( new Message("Le product a ete ajoutee avec success"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProductHandler() {
        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteProductHandler(@PathVariable Long id) throws IOException {
        productService.deleteProduct(id);
        return  ResponseEntity.ok(null);
    }


    private ProductDto convertToProductDtoj(String productDtoObj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(productDtoObj, ProductDto.class);


    }



}
