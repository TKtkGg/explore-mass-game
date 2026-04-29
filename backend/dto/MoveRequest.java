package dto;

import domain.SelectedRoute;

public class MoveRequest {
    private SelectedRoute routeType;

    public MoveRequest(SelectedRoute routeType) {
        this.routeType = routeType;
    }

    public SelectedRoute getRouteType() {
        return routeType;
    }
}