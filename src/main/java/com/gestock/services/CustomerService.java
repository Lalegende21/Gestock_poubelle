package com.gestock.services;

import com.gestock.entites.Customer;
import com.gestock.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
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

    @Cacheable(value = "customer")
    public List<Customer> getAllCustomer() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return this.customerRepository.findAll(sort);
    }

    @Cacheable(value = "customer", key = "#id")
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Aucun client avec cet identifiant trouve !"));
    }


    @Cacheable(value = "customer", key = "#email")
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = this.customerRepository.findByEmail(email);
        return optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Aucun client trouve avec cet email!"));
    }

    @CachePut(value = "customer", key = "#customer.id")
    public void updateCustomer(Long id, Customer customer) {
        Customer customerUpdate = this.getCustomerById(id);

        if (customerUpdate.getId().equals(customer.getId())) {
            customerUpdate.setFirstname(customer.getFirstname());
            customerUpdate.setLastname(customer.getLastname());
            customerUpdate.setEmail(customer.getEmail());
            customerUpdate.setPhoneNumber(customer.getPhoneNumber());
            customerUpdate.setSexe(customer.getSexe());
            this.customerRepository.save(customerUpdate);
        }else {
            throw new RuntimeException("Incoherence entre l'id fourni et l'id du centre a modifie !");
        }
    }

    @CacheEvict(allEntries = true)
    public void deleteAllCustomer() {
        this.customerRepository.deleteAll();
    }

    @CacheEvict(value = "customer", key = "#id")
    public void deleteCustomerById(Long id) {
        this.customerRepository.deleteById(id);
    }

}
