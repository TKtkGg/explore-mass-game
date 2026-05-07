package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.domain.SelectedRoute;
import com.example.backend.dto.move.MoveRequest;
import com.example.backend.dto.move.MoveResponse;
import com.example.backend.exception.GameStoppedException;
import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.PlayerState;

import java.util.Random;

@Service
public class MoveService {
    Random rand = new Random();

    private MoveState moveState;
    private PlayerState playerState;
    public MoveService(MoveState moveState, PlayerState playerState) {
        this.moveState = moveState;
        this.playerState = playerState;
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
        this.moveState.setRemainingSteps(25);
        this.moveState.setStopped(false);
        this.moveState.setRouteType(null);

        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), "");
    }

    public MoveResponse getCurrentMoveState() {
        SelectedRoute[] options = this.moveState.getRandomRouteOptions();
        if(options == null) {
            options = this.getRandomRouteOptions();
        }
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), options, "");
    }

    public SelectedRoute[] getRandomRouteOptions() {
        SelectedRoute[] route = this.moveState.getRouteOptions();
        SelectedRoute[] options = {route[rand.nextInt(route.length)], route[rand.nextInt(route.length)], route[rand.nextInt(route.length)]};
        this.moveState.setRandomRouteOptions(options);
        return options;
    }

    public MoveResponse rest(MoveRequest request) {
        this.moveAbstract(request);
        int healAmount = this.playerState.Heal(100);
        return new MoveResponse(this.moveState.getRouteType(), this.moveState.getRemainingSteps(), this.moveState.isStopped(), this.getRandomRouteOptions(), "休んで" + healAmount + "回復した！");
    }
}