package com.example.section13.controller;

import com.example.section13.controller.dto.CustomerReadDto;
import com.example.section13.mapper.CustomerMapper;
import com.example.section13.model.Customer;
import com.example.section13.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class LoginController {

    CustomerRepository repository;
    CustomerMapper mapper;

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
