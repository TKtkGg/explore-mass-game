package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.card.CardResponse;
import com.example.backend.dto.card.CardsResponse;
import com.example.backend.service.gamestate.card.CardListState;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.character.PlayerState;

@Service
public class CardService {
    private PlayerState playerState;
    private CardListState cardListState;
    private List<CardState> lineup;
    private List<CardState> display;

    public CardService(PlayerState playerState, CardListState cardListState) {
        this.playerState = playerState;
        this.cardListState = cardListState;
        this.lineup = new ArrayList<>();
        this.display = new ArrayList<>();
    }

    public CardResponse showCards() {
        this.lineup.clear();
        this.display.clear();
        this.lineup.addAll(this.getUnownedCards());
        Collections.shuffle(this.lineup);
        this.display.addAll(this.lineup.subList(0, 3 < this.lineup.size() ? 3 : this.lineup.size()));
        return new CardResponse(this.display, null);
    }

    public CardResponse chooseCard(CardRequest request) {
        this.playerState.addCard(request.getChosenCard());
        return new CardResponse(this.display, request.getChosenCard());
    }

    public List<CardState> getUnownedCards() {
        return Arrays.stream(this.cardListState.getCardList()).filter(card -> !this.playerState.getOwnedCards().contains(card)).collect(Collectors.toList());
    }

    public CardsResponse showOwnedCards() {
        return new CardsResponse(this.playerState.getOwnedCards());
    }
}
