package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.move.MoveRequest;
import com.example.backend.dto.move.MoveResponse;
import com.example.backend.service.MoveService;

@RestController
public class MoveController {
    private MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @PostMapping("/move")
    public MoveResponse move(@RequestBody MoveRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.moveService.move(request, sessionId);
    }

    @PostMapping("/move/rest")
    public MoveResponse rest(@RequestBody MoveRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.moveService.rest(request, sessionId);
    }

    @GetMapping("/move/status")
    public MoveResponse getCurrentMoveState(@RequestHeader("X-Session-Id") String sessionId) {
        return this.moveService.getCurrentMoveState(sessionId);
    }
}