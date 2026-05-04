package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.MoveRequest;
import com.example.backend.dto.MoveResponse;
import com.example.backend.domain.SelectedRoute;
import com.example.backend.exception.GameStoppedException;

import java.util.Random;

@Service
public class MoveService {
    Random rand = new Random();

    int remainingSteps = 25;
    boolean stopped = false;
    SelectedRoute routeType;

    public MoveResponse move(MoveRequest request) {
        this.routeType = request.getRouteType();
        if(!this.stopped) {
            this.remainingSteps--;
        } else {
            throw new GameStoppedException("ゲームが停止しました。");
        }
        if(this.remainingSteps <= 0) {
            this.stopped = true;
        }
        return new MoveResponse(this.routeType, this.remainingSteps, this.stopped, this.getRandomRouteOptions());
    }

    public MoveResponse reset() {
        this.remainingSteps = 25;
        this.stopped = false;
        this.routeType = null;

        return new MoveResponse(this.routeType, this.remainingSteps, this.stopped, this.getRandomRouteOptions());
    }

    public String[] getRandomRouteOptions() {
        String[] route = {"BATTLE", "TREASURE", "REST", "CARD", "SHOP"};
        String[] options = {route[rand.nextInt(route.length)], route[rand.nextInt(route.length)], route[rand.nextInt(route.length)]};
        return options;
    }
}