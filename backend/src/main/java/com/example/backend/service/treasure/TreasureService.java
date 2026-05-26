package com.example.backend.service.treasure;

import java.util.Random;

import com.example.backend.service.gamestate.treasure.TreasureState;
import com.example.backend.dto.TreasureResponse;
import org.springframework.stereotype.Service;

@Service
public class TreasureService {
    Random rand = new Random();
    private StatusTreasureService statusTreasureService;
    private EquipmentTreasureService equipmentTreasureService;
    private TreasureState treasureState;

    public TreasureService(StatusTreasureService statusTreasureService, EquipmentTreasureService equipmentTreasureService, TreasureState treasureState) {
        this.statusTreasureService = statusTreasureService;
        this.equipmentTreasureService = equipmentTreasureService;
        this.treasureState = treasureState;
    }

    public TreasureResponse showTreasure() {
        int treasureNum = rand.nextInt(2);
        if(treasureNum == 0) {
            this.treasureState.setAppearedTreasure("ステータス宝箱");
            return new TreasureResponse("ステータス宝箱を見つけた！");
        } else {
            this.treasureState.setAppearedTreasure("装備宝箱");
            return new TreasureResponse("装備宝箱を見つけた！");
        }
    }

    public TreasureResponse open() {
        if(this.treasureState.getAppearedTreasure().equals("ステータス宝箱")) {
            return new TreasureResponse(this.statusTreasureService.open());
        } else {
            return new TreasureResponse(this.equipmentTreasureService.open());
        }
    }
}
