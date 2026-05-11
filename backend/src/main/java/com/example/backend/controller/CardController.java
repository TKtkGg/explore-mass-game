package com.example.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.service.CardService;
import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.card.CardResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class CardController {
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card")
    public CardResponse showCards() {
        return this.cardService.showCards();
    }
    
    @PostMapping("/card/choose")
    public CardResponse chooseCard(@RequestBody CardRequest request) {
        return this.cardService.chooseCard(request);
    }
}
