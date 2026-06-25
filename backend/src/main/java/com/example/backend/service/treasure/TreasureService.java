package com.example.backend.service.treasure;

import java.util.Random;

import com.example.backend.dto.TreasureResponse;
import org.springframework.stereotype.Service;
import com.example.backend.service.gamestate.session.GameSession;
import com.example.backend.service.GameSessionManager;

@Service
public class TreasureService {
    Random rand = new Random();
    private StatusTreasureService statusTreasureService;
    private EquipmentTreasureService equipmentTreasureService;
    private GameSessionManager gameSessionManager;

    public TreasureService(StatusTreasureService statusTreasureService, EquipmentTreasureService equipmentTreasureService, GameSessionManager gameSessionManager) {
        this.statusTreasureService = statusTreasureService;
        this.equipmentTreasureService = equipmentTreasureService;
        this.gameSessionManager = gameSessionManager;
    }

    public TreasureResponse showTreasure(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        int treasureNum = rand.nextInt(2);
        if(treasureNum == 0) {
            gameSession.getTreasureState().setAppearedTreasure("ステータス宝箱");
        } else {
            gameSession.getTreasureState().setAppearedTreasure("装備宝箱");
        }
        return new TreasureResponse("宝箱を見つけた！");
    }

    public TreasureResponse open(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        if(gameSession.getTreasureState().getAppearedTreasure().equals("ステータス宝箱")) {
            return new TreasureResponse(this.statusTreasureService.open(gameSession));
        } else {
            return new TreasureResponse(this.equipmentTreasureService.open(gameSession));
        }
    }
}
