package com.gestock.services;

import com.gestock.dto.ProductDto;
import com.gestock.entites.Product;
import com.gestock.exceptions.ProductNotFoundException;
import com.gestock.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final FileService fileService;

    @Value("${poster}")
    private  String path;


    @Value("${baseUrl}")
    private  String baseUrl;

    public ProductService(ProductRepository productRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    //Methide pour recuperer tous les produits
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return productRepository.findAll(pageable);
    }

//    public List<Product> getAllProducts() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        return this.productRepository.findAll(sort);
//    }

    //Methode pour enregistrer un produit
    public ProductDto saveProduct(ProductDto productDto, MultipartFile file) throws IOException {

        String uploadFileName = fileService.uploadFile(path, file);
        productDto.setPoster(uploadFileName);


        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setPoster(productDto.getPoster());

        this.productRepository.save(product);
        return productDto;
    }


    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();


        // 2. iterable through the list, generate posterUrl for each student obj
        // and map to StudentDto obj
        for (Product product : products) {
            String postUrl = baseUrl + "/file/" + product.getPoster();

            ProductDto productDto = new ProductDto(
                    product.getId(),
                   product.getName(),
                  product.getDescription(),
                  product.getPrice(),
                  product.getPoster(),
                    postUrl


            );
            productDtos.add(productDto);
        }

        return  productDtos;
    }



    public String deleteProduct(Long id) throws IOException {

        //1. check if student object exist in DB
        Product pt = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Student not found with id = " + id));
//        Video vd = videoRepository.findByStudentId()

        // 2. Delete the file associated with this
        // object


        Files.deleteIfExists(Paths.get(path + File.separator + pt.getPoster()));



        // 3.delete the student object
        productRepository.deleteById(id);



        return "student deleted with id = " +id;

    }


}
