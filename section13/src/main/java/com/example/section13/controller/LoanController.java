package com.example.section13.controller;

import com.example.section13.model.Customer;
import com.example.section13.model.Loan;
import com.example.section13.repository.CustomerRepository;
import com.example.section13.repository.LoanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoanController {

    LoanRepository loanRepository;
    CustomerRepository customerRepository;

    @GetMapping("/my-loans")
    @PostAuthorize("hasRole('USER')")
    public List<Loan> getLoanDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findAllByEmailIgnoreCase(email);
        if (customers != null && !customers.isEmpty()) {
            List<Loan> loans = loanRepository.findAllByCustomerIdOrderByStartedAtDesc(customers.getFirst().getId());
            if (!loans.isEmpty()) {
                return loans;
            } else {
                return Collections.emptyList();
            }
        }
        return null;
    }

}
