package com.example.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.backend.dto.UserDto;
import com.example.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

}
