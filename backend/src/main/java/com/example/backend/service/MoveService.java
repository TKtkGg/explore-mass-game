package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.MoveRequest;
import com.example.backend.dto.MoveResponse;
import com.example.backend.domain.SelectedRoute;

@Service
public class MoveService {
    int remainingSteps = 25;
    boolean stopped = false;
    SelectedRoute routeType;

    public MoveResponse move(MoveRequest request) {
        this.routeType = request.getRouteType();
        if(!this.stopped) {
            this.remainingSteps--;
        }
        if(this.remainingSteps <= 0) {
            this.stopped = true;
        }
        return new MoveResponse(this.routeType, this.remainingSteps, this.stopped);
    }
}