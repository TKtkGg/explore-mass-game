package com.example.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

public class UsersDto {
    
    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
