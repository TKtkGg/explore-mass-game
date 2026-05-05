package com.example.backend.dto.move;

import com.example.backend.domain.SelectedRoute;

public class MoveRequest {
    private SelectedRoute routeType;

    public MoveRequest(SelectedRoute routeType) {
        this.routeType = routeType;
    }

    public SelectedRoute getRouteType() {
        return routeType;
    }
}