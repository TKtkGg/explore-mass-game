package com.example.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.example.backend.dto.UsersDto;
import com.example.backend.entity.Users;
import com.example.backend.service.UsersPrincipal;
import com.example.backend.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/login")
    public Map<String, String> login(@RequestBody UsersDto usersDto, HttpServletRequest request) {
        Users user = usersService.findByEmail(usersDto.getEmail());
        if (user == null) {
            return Map.of("message", "User not found");
        }
        if (!passwordEncoder.matches(usersDto.getPassword(), user.getPassword())) {
            return Map.of("message", "Invalid password");
        }

        UsersPrincipal principal = new UsersPrincipal(user);
        UsernamePasswordAuthenticationToken authentication = 
            new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities()
            );
        
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(
            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
            context
        );


        return Map.of("message", "ok");
    }

    @GetMapping("/auth/user")
    public ResponseEntity<?> user(@AuthenticationPrincipal UsersPrincipal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body(Map.of("authenticated", false));
        }

        return ResponseEntity.ok(Map.of(
            "authenticated", true,
            "email", principal.getEmail()
        ));
    }

    @PostMapping("/auth/logout")
    public Map<String, String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return Map.of("message", "ok");
    }
}
