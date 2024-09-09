package com.gestock.repositories;

import com.gestock.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Long, Customer> {
}
