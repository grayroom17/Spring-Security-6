package com.example.section12.controller;

import com.example.section12.model.Transaction;
import com.example.section12.repository.TransactionRepository;
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

    @GetMapping("/my-balance")
    public List<Transaction> getBalanceDetails(@RequestParam int id) {
        List<Transaction> transactions = transactionRepository.findAllByCustomerIdOrderByTransactionDateDesc(id);
        if (!transactions.isEmpty()) {
            return transactions;
        } else {
            return Collections.emptyList();
        }
    }
}
