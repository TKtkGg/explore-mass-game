package com.example.backend.service.treasure;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.treasure.StatusTreasureState;

@Service
public class StatusTreasureService {
    private PlayerState playerState;
    private StatusTreasureState sts;

    public StatusTreasureService(PlayerState playerState, StatusTreasureState statusTreasureState) {
        this.playerState = playerState;
        this.sts = statusTreasureState;
    }

    public String open() {
        int randomIndex = sts.getRandomIndex();
        sts.setTargetName(sts.getStatusArray()[randomIndex]);
        if(sts.getTargetName().equals("HP")) {
            this.sts.setPoint(10);
        } else {
            this.sts.setPoint(1);
        }

        sts.setPoint(applyCards(sts.getPoint()));

        switch(sts.getTargetName()) {
			case "HP":
				playerState.setMaxHp(playerState.getMaxHp() + sts.getPoint());
                playerState.setHp(playerState.getHp() + sts.getPoint());
				break;
			case "ATK":
				playerState.setAtk(playerState.getOriginalAtk() + sts.getPoint());
				break;
			case "DEF":
				playerState.setDef(playerState.getDef() + sts.getPoint());
				break;
			case "SPD":
				playerState.setSpd(playerState.getSpd() + sts.getPoint());
				break;
			default:
				break;
		}

        return sts.getTargetName() + "が" + this.sts.getPoint() + "上昇した！";
    }

    public int applyCards(int point) {
        for(CardState card : this.playerState.getOwnedCards()) {
            if(card.getName().equals("ラッキー")) {
                point = point * 2;
            }
        }
        return point;
    }
}
