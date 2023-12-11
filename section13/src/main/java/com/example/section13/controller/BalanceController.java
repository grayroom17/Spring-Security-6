package com.example.section13.controller;

import com.example.section13.model.Customer;
import com.example.section13.model.Transaction;
import com.example.section13.repository.CustomerRepository;
import com.example.section13.repository.TransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceController {

    TransactionRepository transactionRepository;
    CustomerRepository customerRepository;

    @GetMapping("/my-balance")
    public List<Transaction> getBalanceDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findAllByEmailIgnoreCase(email);
        if (customers != null && !customers.isEmpty()) {
            List<Transaction> transactions = transactionRepository.findAllByCustomerIdOrderByTransactionDateDesc(customers.getFirst().getId());
            if (!transactions.isEmpty()) {
                return transactions;
            } else {
                return Collections.emptyList();
            }
        }
        return null;
    }
}
