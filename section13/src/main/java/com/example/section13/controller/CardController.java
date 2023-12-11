package com.example.section13.controller;

import com.example.section13.model.Card;
import com.example.section13.model.Customer;
import com.example.section13.repository.CardRepository;
import com.example.section13.repository.CustomerRepository;
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
@RequestMapping("/cards")
public class CardController {

    CardRepository cardRepository;
    CustomerRepository customerRepository;

    @GetMapping("/my-cards")
    public List<Card> getCardDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findAllByEmailIgnoreCase(email);
        if (customers != null && !customers.isEmpty()) {
            List<Card> cards = cardRepository.findByCustomerId(customers.getFirst().getId());
            if (!cards.isEmpty()) {
                return cards;
            } else {
                return Collections.emptyList();
            }
        }
        return null;
    }

}
