package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.move.MoveRequest;
import com.example.backend.dto.move.MoveResponse;
import com.example.backend.service.MoveService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class MoveController {
    private MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @PostMapping("/move")
    public MoveResponse move(@RequestBody MoveRequest request) {
        return this.moveService.move(request);
    }

    @PostMapping("/reset")
    public MoveResponse reset() {
        return this.moveService.reset();
    }

    @PostMapping("/move/rest")
    public MoveResponse rest(@RequestBody MoveRequest request) {
        return this.moveService.rest(request);
    }

    @PostMapping("/move/treasure")
    public MoveResponse treasure(@RequestBody MoveRequest request) {
        return this.moveService.treasure(request);
    }

    @GetMapping("/move/status")
    public MoveResponse getCurrentMoveState() {
        return this.moveService.getCurrentMoveState();
    }
}