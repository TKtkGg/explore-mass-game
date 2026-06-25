package com.example.backend.service.gamestate;

import com.example.backend.domain.SelectedRoute;

public class MoveState {
    int remainingSteps ;
    boolean stopped;
    SelectedRoute routeType;
    SelectedRoute[] routeOptions;
    SelectedRoute[] randomRouteOptions;

    public MoveState() {
        this.remainingSteps = 25;
        this.stopped = false;
        this.routeType = null;
        this.routeOptions = new SelectedRoute[] {SelectedRoute.BATTLE, SelectedRoute.TREASURE, SelectedRoute.REST, SelectedRoute.CARD, SelectedRoute.SHOP};
        this.randomRouteOptions = null;
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
    public SelectedRoute[] getRouteOptions() {
        return routeOptions;
    }
    public SelectedRoute[] getRandomRouteOptions() {
        return randomRouteOptions;
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
    public void setRandomRouteOptions(SelectedRoute[] randomRouteOptions) {
        this.randomRouteOptions = randomRouteOptions;
    }
}