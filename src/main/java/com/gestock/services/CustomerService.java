package com.gestock.services;

import com.gestock.entites.Customer;
import com.gestock.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;


    public void createCustomer(Customer customer){
        this.customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Aucun client avec cet identifiant trouve !"));
    }

    public void getCustomerByEmail(Long id) {}

    public void updateCustomer(Long id, Customer customer) {}

    public void deleteAllCustomer() {}

    public void deleteCustomerById() {}

}
