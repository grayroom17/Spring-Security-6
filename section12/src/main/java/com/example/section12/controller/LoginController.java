package com.example.section12.controller;

import com.example.section12.model.Customer;
import com.example.section12.controller.dto.CustomerCreateDto;
import com.example.section12.controller.dto.CustomerReadDto;
import com.example.section12.mapper.CustomerMapper;
import com.example.section12.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class LoginController {

    CustomerRepository repository;
    CustomerMapper mapper;
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerCreateDto dto) {
        Customer customer = mapper.fromCreateDto(dto);

        ResponseEntity response;
        try {
            String hashPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPassword);
            customer.setCreatedAt(Date.valueOf(LocalDate.now()));
            repository.save(customer);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(mapper.toReadDto(customer));
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception due to" + e.getMessage());
        }
        return response;
    }

    @GetMapping("/user")
    public CustomerReadDto getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = repository.findAllByEmailIgnoreCase(authentication.getName());
        if (!customers.isEmpty()) {
            return mapper.toReadDto(customers.get(0));
        } else {
            return null;
        }

    }

}
