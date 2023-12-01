package com.example.section9.repository;

import com.example.section9.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByEmailIgnoreCase(String email);

}
