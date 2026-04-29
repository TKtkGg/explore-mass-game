package com.example.backend.dto;

import com.example.backend.domain.SelectedRoute;

public class MoveResponse {
    private SelectedRoute routeType;
    private int remainingSteps;
    private boolean stopped;

    public MoveResponse(SelectedRoute routeType, int remainingSteps, boolean stopped) {
        this.routeType = routeType;
        this.remainingSteps = remainingSteps;
        this.stopped = stopped;
    }

    public SelectedRoute getRouteType() {
        return this.routeType;
    }

    public int getRemainingSteps() {
        return this.remainingSteps;
    }

    public boolean getStopped() {
        return this.stopped;
    }
    
}