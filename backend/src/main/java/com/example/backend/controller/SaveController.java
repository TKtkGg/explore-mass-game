package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.backend.service.SaveService;
import com.example.backend.service.UserPrincipal;

@RestController
public class SaveController {
    private SaveService saveService;

    public SaveController(SaveService saveService) {
        this.saveService = saveService;
    }

    @PostMapping("/save")
    public Map<String, String> save(
        @RequestHeader("X-Session-Id") String sessionId,
        @AuthenticationPrincipal UserPrincipal principal
    ) throws Exception{
        saveService.save(sessionId, principal.getUserId());
        return Map.of("message", "ok");
    }

    @GetMapping("/save")
    public Map<String, String> load(@AuthenticationPrincipal UserPrincipal principal) throws Exception {
        String sessionId = saveService.load(principal.getUserId());
        if (sessionId == null) {
            return Map.of("message", "no save data");
        }
        return Map.of("sessionId", sessionId);
    }
}
