package com.example.backend.dto.move;

import com.example.backend.domain.SelectedRoute;

public class MoveResponse {
    private SelectedRoute routeType;
    private int remainingSteps;
    private boolean stopped;
    private SelectedRoute[] routeOptions;

    public MoveResponse(SelectedRoute routeType, int remainingSteps, boolean stopped, SelectedRoute[] routeOptions) {
        this.routeType = routeType;
        this.remainingSteps = remainingSteps;
        this.stopped = stopped;
        this.routeOptions = routeOptions;
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

    public SelectedRoute[] getRouteOptions() {
        return this.routeOptions;
    }
}