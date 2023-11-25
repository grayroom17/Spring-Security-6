package com.example.section5.controller;

import com.example.section5.controller.dto.CustomerCreateDto;
import com.example.section5.mapper.CustomerMapper;
import com.example.section5.model.Customer;
import com.example.section5.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
