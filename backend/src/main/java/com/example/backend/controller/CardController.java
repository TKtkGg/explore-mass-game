package com.example.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.backend.service.CardService;
import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.card.CardResponse;
import com.example.backend.dto.card.CardsResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class CardController {
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card")
    public CardResponse showCards(@RequestHeader("X-Session-Id") String sessionId) {
        return this.cardService.showCards(sessionId);
    }

    @GetMapping("/cards")
    public CardsResponse showOwnedCards(@RequestHeader("X-Session-Id") String sessionId) {
        return this.cardService.showOwnedCards(sessionId);
    }
    
    @PostMapping("/card/choose")
    public CardResponse chooseCard(@RequestBody CardRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.cardService.chooseCard(request, sessionId);
    }
}
