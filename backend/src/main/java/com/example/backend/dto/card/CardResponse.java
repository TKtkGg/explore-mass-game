package com.example.backend.dto.card;

import java.util.List;

import com.example.backend.service.gamestate.CardState;

public class CardResponse {
    private List<CardState> display;
    private CardState chosenCard;

    public CardResponse(List<CardState> display, CardState chosenCard) {
        this.display = display;
        this.chosenCard = chosenCard;
    }

    public List<CardState> getDisplay() {
        return this.display;
    }

    public CardState getChosenCard() {
        return this.chosenCard;
    }
}
