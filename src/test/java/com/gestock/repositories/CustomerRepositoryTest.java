package com.gestock.repositories;

import com.gestock.entites.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shoulReturnAllCustomers() {
        //Arrange
        Customer customerOne = Customer.builder()
                .id(1L)
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@gmail.com")
                .sexe("homme")
                .phoneNumber("67489755")
                .dob("30/09/2024")
                .build();

        Customer customerTwo = Customer.builder()
                .id(2L)
                .firstname("Jane")
                .lastname("Doe")
                .email("janedoe@gmail.com")
                .sexe("femme")
                .phoneNumber("672679974")
                .dob("20/09/2024")
                .build();

        this.customerRepository.saveAll(List.of(customerOne, customerTwo));

        //Act
        final List<Customer> customerList = this.customerRepository.findAll();

        //Asserts
        Assertions.assertEquals(2, customerList.size());
    }

    @Test
    void shouldReturnUserByEmail() {
        //Arrange
        Customer customerOne = Customer.builder()
                .id(1L)
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@gmail.com")
                .sexe("homme")
                .phoneNumber("67489755")
                .dob("30/09/2024")
                .build();

        Customer customerTwo = Customer.builder()
                .id(2L)
                .firstname("Jane")
                .lastname("Doe")
                .email("janedoe@gmail.com")
                .sexe("femme")
                .phoneNumber("672679974")
                .dob("20/09/2024")
                .build();

        this.customerRepository.saveAll(List.of(customerOne, customerTwo));

        //Act
        final Optional<Customer> customer = this.customerRepository.findByEmail("johndoe@gmail.com");

        //Asserts
        Assertions.assertEquals(customerTwo.getId(), customer.get().getId());
        Assertions.assertEquals(customerTwo.getEmail(), customer.get().getEmail());
    }
}