package com.example.backend.service.gamestate.session;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.BattleState;
import com.example.backend.service.gamestate.treasure.TreasureState;
import com.example.backend.service.gamestate.shop.ShopState;

public class GameSession {
    private String sessionId;
    private PlayerState playerState;
    private MoveState moveState;
    private BattleState battleState;
    private TreasureState treasureState;
    private ShopState shopState;

    public GameSession(String sessionId, PlayerState playerState, MoveState moveState, BattleState battleState, TreasureState treasureState, ShopState shopState) {
        this.sessionId = sessionId;
        this.playerState = playerState;
        this.moveState = moveState;
        this.battleState = battleState;
        this.treasureState = treasureState;
        this.shopState = shopState;
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
}
