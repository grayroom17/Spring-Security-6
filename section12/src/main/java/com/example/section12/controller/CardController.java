package com.example.section12.controller;

import com.example.section12.model.Card;
import com.example.section12.repository.CardRepository;
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

    @GetMapping("/my-cards")
    public List<Card> getCardDetails(@RequestParam int id) {
        List<Card> cards = cardRepository.findByCustomerId(id);
        if (!cards.isEmpty()) {
            return cards;
        } else {
            return Collections.emptyList();
        }
    }

}
