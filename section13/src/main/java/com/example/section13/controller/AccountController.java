package com.example.section13.controller;

import com.example.section13.model.Account;
import com.example.section13.model.Customer;
import com.example.section13.repository.AccountRepository;
import com.example.section13.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    @GetMapping("/my-account")
    public Account getAccountDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findAllByEmailIgnoreCase(email);
        if (customers != null && !customers.isEmpty()) {
            Account account = accountRepository.findByCustomerId(customers.getFirst().getId());
            if (account != null) {
                return account;
            }
        }
        return null;
    }

}
