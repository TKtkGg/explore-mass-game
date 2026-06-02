package com.example.backend.dto.card;

import java.util.List;

import com.example.backend.service.gamestate.card.CardState;

public class CardsResponse {
    private List<CardState> ownedCards;

    public CardsResponse(List<CardState> ownedCards) {
        this.ownedCards = ownedCards;
    }
    
    public List<CardState> getOwnedCards() {
        return this.ownedCards;
    }
}
