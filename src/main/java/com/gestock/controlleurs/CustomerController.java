package com.gestock.controlleurs;

import com.gestock.entites.Customer;
import com.gestock.services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private CustomerService customerService;


    @PostMapping(path = "/save-customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        this.customerService.createCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = this.customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(path = "/get-customer/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return this.customerService.getCustomerById(id);
    }

}
