package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.domain.SelectedRoute;
import com.example.backend.dto.move.MoveRequest;
import com.example.backend.dto.move.MoveResponse;
import com.example.backend.exception.GameStoppedException;
import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.character.PlayerState;

import java.util.Random;
import java.util.Arrays;

@Service
public class MoveService {
    Random rand = new Random();

    private MoveState moveState;
    private PlayerState playerState;
    private StatusTreasureService treasureService;
    private EquipmentTreasureService equipmentTreasureService;
    private CardService cardService;

    public MoveService(MoveState moveState, PlayerState playerState, StatusTreasureService treasureService, EquipmentTreasureService equipmentTreasureService, CardService cardService) {
        this.moveState = moveState;
        this.playerState = playerState;
        this.treasureService = treasureService;
        this.equipmentTreasureService = equipmentTreasureService;
        this.cardService = cardService;
    }

    public void moveAbstract(MoveRequest request) {
        this.moveState.setRouteType(request.getRouteType());
        if(!this.moveState.isStopped()) {
            this.moveState.setRemainingSteps(this.moveState.getRemainingSteps() - 1);
        } else {
            throw new GameStoppedException("ゲームが停止しました。");
        }
        if(this.moveState.getRemainingSteps() <= 0) {
            this.moveState.setStopped(true);
        }
    }

    public MoveResponse move(MoveRequest request) {
        this.moveAbstract(request);
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), "");
    }

    public MoveResponse reset() {
        this.playerState.init();
        this.moveState.setRemainingSteps(25);
        this.moveState.setStopped(false);
        this.moveState.setRouteType(null);

        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), "");
    }

    public MoveResponse getCurrentMoveState() {
        SelectedRoute[] options = this.moveState.getRandomRouteOptions();
        if(shouldRegenerate(options)) {
            options = this.getRandomRouteOptions();
        }
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), options, "");
    }

    public SelectedRoute[] getRandomRouteOptions() {
        SelectedRoute[] route = this.moveState.getRouteOptions();
        if(cardService.getUnownedCards().isEmpty()) {
            SelectedRoute[] newRoute = new SelectedRoute[route.length - 1];
            int index = 0;
            for(SelectedRoute r : route) {
                if(r == SelectedRoute.CARD) {
                    continue;
                }
                newRoute[index] = r;
                index++;
            }
            route = newRoute;
        }
        SelectedRoute[] options = {route[rand.nextInt(route.length)], route[rand.nextInt(route.length)], route[rand.nextInt(route.length)]};
        this.moveState.setRandomRouteOptions(options);
        return options;
    }

    public MoveResponse rest(MoveRequest request) {
        this.moveAbstract(request);
        int healAmount = this.playerState.Heal(100);
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), "休んで" + healAmount + "回復した！");
    }

    public MoveResponse treasure(MoveRequest request) {
        this.moveAbstract(request);
        String message = "";
        int treasureNum = rand.nextInt(2);
        if(treasureNum == 0) {
            message = "ステータス宝箱を見つけた！";
            message += this.treasureService.open();
        } else {
            message = "装備宝箱を見つけた！";
            message += this.equipmentTreasureService.open();
        }
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), message);
    }

    private boolean shouldRegenerate(SelectedRoute[] options) {
        if (options == null) return true;
        return Arrays.asList(options).contains(SelectedRoute.CARD) && cardService.getUnownedCards().isEmpty();
    }
}