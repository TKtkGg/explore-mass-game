package com.example.backend.service.gamestate.session;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.BattleState;
import com.example.backend.service.gamestate.treasure.TreasureState;
import com.example.backend.service.gamestate.shop.ShopState;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.character.EnemyState;

import java.util.List;
import java.util.ArrayList;

public class GameSession {
    private String sessionId;
    private PlayerState playerState;
    private MoveState moveState;
    private BattleState battleState;
    private TreasureState treasureState;
    private ShopState shopState;
    private EnemyState enemyState;
    private List<CardState> cardLineup = new ArrayList<>();
    private List<CardState> cardDisplay = new ArrayList<>();

    public GameSession(String sessionId, PlayerState playerState, MoveState moveState, BattleState battleState, TreasureState treasureState, ShopState shopState, EnemyState enemyState) {
        this.sessionId = sessionId;
        this.playerState = playerState;
        this.moveState = moveState;
        this.battleState = battleState;
        this.treasureState = treasureState;
        this.shopState = shopState;
        this.enemyState = enemyState;
    }

    public String getSessionId() {
        return sessionId;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public MoveState getMoveState() {
        return moveState;
    }

    public BattleState getBattleState() {
        return battleState;
    }

    public TreasureState getTreasureState() {
        return treasureState;
    }

    public ShopState getShopState() {
        return shopState;
    }

    public EnemyState getEnemyState() {
        return enemyState;
    }

    public List<CardState> getCardLineup() {
        return cardLineup;
    }

    public List<CardState> getCardDisplay() {
        return cardDisplay;
    }
}
