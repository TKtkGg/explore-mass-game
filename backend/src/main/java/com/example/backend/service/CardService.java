package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.card.CardResponse;
import com.example.backend.dto.card.CardsResponse;
import com.example.backend.service.gamestate.card.CardListState;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class CardService {
    private CardListState cardListState;
    private GameSessionManager gameSessionManager;

    public CardService(GameSessionManager gameSessionManager, CardListState cardListState) {
        this.gameSessionManager = gameSessionManager;
        this.cardListState = cardListState;
    }

    public CardResponse showCards(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        gameSession.getCardLineup().clear();
        gameSession.getCardDisplay().clear();
        gameSession.getCardLineup().addAll(getUnownedCards(gameSession));
        Collections.shuffle(gameSession.getCardLineup());
        gameSession.getCardDisplay().addAll(gameSession.getCardLineup().subList(0, 3 < gameSession.getCardLineup().size() ? 3 : gameSession.getCardLineup().size()));
        return new CardResponse(gameSession.getCardDisplay(), null);
    }

    public CardResponse chooseCard(CardRequest request, String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        gameSession.getPlayerState().addCard(request.getChosenCard());
        return new CardResponse(gameSession.getCardDisplay(), request.getChosenCard());
    }

    public List<CardState> getUnownedCards(GameSession gameSession) {
        return Arrays.stream(this.cardListState.getCardList()).filter(card -> !gameSession.getPlayerState().getOwnedCards().contains(card)).collect(Collectors.toList());
    }

    public CardsResponse showOwnedCards(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        return new CardsResponse(gameSession.getPlayerState().getOwnedCards());
    }
}
