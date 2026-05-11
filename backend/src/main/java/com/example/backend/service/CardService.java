package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import com.example.backend.service.gamestate.PlayerState;
import com.example.backend.service.gamestate.CardListState;
import com.example.backend.service.gamestate.CardState;
import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.card.CardResponse;

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
        this.lineup.addAll(Arrays.asList(this.cardListState.getCardList()));
        Collections.shuffle(this.lineup);
        this.display.addAll(this.lineup.subList(0, 3));
        return new CardResponse(this.display, null);
    }

    public CardResponse chooseCard(CardRequest request) {
        this.lineup.remove(request.getChosenCard());
        this.display.remove(request.getChosenCard());
        this.playerState.addCard(request.getChosenCard());
        return new CardResponse(this.display, request.getChosenCard());
    }
}
