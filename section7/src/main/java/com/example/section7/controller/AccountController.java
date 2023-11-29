package com.example.section7.controller;

import com.example.section7.model.Account;
import com.example.section7.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    AccountRepository accountRepository;

    @GetMapping("/my-account")
    public Account getAccountDetails(@RequestParam int id) {
        Account account = accountRepository.findByCustomerId(id);
        if (account != null) {
            return account;
        } else {
            return null;
        }
    }

}
