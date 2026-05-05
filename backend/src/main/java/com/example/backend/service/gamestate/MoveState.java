package com.example.backend.service.gamestate;

import org.springframework.stereotype.Service;

import com.example.backend.domain.SelectedRoute;

@Service
public class MoveState {
    int remainingSteps ;
    boolean stopped;
    SelectedRoute routeType;
    String[] routeOptions;

    public MoveState() {
        this.remainingSteps = 25;
        this.stopped = false;
        this.routeType = null;
        this.routeOptions = new String[] {"BATTLE", "TREASURE", "REST", "CARD", "SHOP"};

    }
    public int getRemainingSteps() {
        return remainingSteps;
    }
    public boolean isStopped() {
        return stopped;
    }
    public SelectedRoute getRouteType() {
        return routeType;
    }
    public String[] getRouteOptions() {
        return routeOptions;
    }

    public void setRemainingSteps(int remainingSteps) {
        this.remainingSteps = remainingSteps;
    }
    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
    public void setRouteType(SelectedRoute routeType) {
        this.routeType = routeType;
    }
}