package com.example.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.UsersDto;
import com.example.backend.entity.Users;
import com.example.backend.service.UsersService;

import jakarta.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/auth/register")
    public Map<String, String> register(@RequestBody @Valid UsersDto usersDto) {
        Users existing = usersService.findByEmail(usersDto.getEmail());

        if (existing != null) {
            return Map.of("message", "Email already exists");
        }
        usersService.save(usersDto);
        return Map.of("message", "User registered successfully");
    }

}
