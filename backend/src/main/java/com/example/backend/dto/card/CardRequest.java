package com.example.backend.dto.card;

import com.example.backend.service.gamestate.CardState;

public class CardRequest {
    private CardState chosenCard;

    public CardRequest(CardState chosenCard) {
        this.chosenCard = chosenCard;
    }

    public CardState getChosenCard() {
        return this.chosenCard;
    }
}
