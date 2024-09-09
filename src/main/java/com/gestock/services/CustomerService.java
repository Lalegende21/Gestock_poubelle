package com.gestock.services;

import com.gestock.entites.Customer;
import com.gestock.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;


    public void createCustomer(){}

    public void getAllCustomer() {}

    public void getCustomerById(Long id) {}

    public void getCustomerByEmail(Long id) {}

    public void updateCustomer(Long id, Customer customer) {}

    public void deleteAllCustomer() {}

    public void deleteCustomerById() {}

}
