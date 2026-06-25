package com.example.backend.service.treasure;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.treasure.StatusTreasureState;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class StatusTreasureService {
    private StatusTreasureState sts;

    public StatusTreasureService(StatusTreasureState statusTreasureState) {
        this.sts = statusTreasureState;
    }

    public String open(GameSession gameSession) {
        int randomIndex = sts.getRandomIndex();
        String targetName = sts.getStatusArray()[randomIndex];
        int point = 0;
        if(targetName.equals("HP")) {
            point = 10;
        } else {
            point = 1;
        }
        point = applyCards(gameSession, point);

        switch(targetName) {
			case "HP":
				gameSession.getPlayerState().setMaxHp(gameSession.getPlayerState().getMaxHp() + point);
                gameSession.getPlayerState().setHp(gameSession.getPlayerState().getHp() + point);
				break;
			case "ATK":
				gameSession.getPlayerState().setAtk(gameSession.getPlayerState().getOriginalAtk() + point);
				break;
			case "DEF":
				gameSession.getPlayerState().setDef(gameSession.getPlayerState().getDef() + point);
				break;
			case "SPD":
				gameSession.getPlayerState().setSpd(gameSession.getPlayerState().getSpd() + point);
				break;
			default:
				break;
		}

        return targetName + "が" + point + "上昇した！";
    }

    public int applyCards(GameSession gameSession, int point) {
        for(CardState card : gameSession.getPlayerState().getOwnedCards()) {
            if(card.getName().equals("ラッキー")) {
                point = point * 2;
            }
        }
        return point;
    }
}
