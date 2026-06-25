package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.equipment.EquipmentRequest;
import com.example.backend.dto.equipment.EquipmentResponse;
import com.example.backend.dto.status.StatusResponse;
import com.example.backend.service.StatusService;

@RestController
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public StatusResponse status(@RequestHeader("X-Session-Id") String sessionId) {
        return this.statusService.status(sessionId);
    }

    @GetMapping("/equipment")
    public EquipmentResponse equipment(@RequestHeader("X-Session-Id") String sessionId) {
        return this.statusService.equipment(sessionId);
    }

    @PostMapping("/equipment/change")
    public EquipmentResponse changeEquipment(@RequestBody EquipmentRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.statusService.changeEquipment(request, sessionId);
    }
}
