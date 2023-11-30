package com.example.section8.controller;

import com.example.section8.model.Loan;
import com.example.section8.repository.LoanRepository;
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
@RequestMapping("/loans")
public class LoanController {

    LoanRepository loanRepository;

    @GetMapping("/my-loans")
    public List<Loan> getLoanDetails(@RequestParam int id) {
        List<Loan> loans = loanRepository.findAllByCustomerIdOrderByStartedAtDesc(id);
        if (!loans.isEmpty()) {
            return loans;
        } else {
            return Collections.emptyList();
        }
    }

}
