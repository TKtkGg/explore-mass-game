package com.example.backend.service;

import com.example.backend.repository.UsersRepository;
import com.example.backend.entity.Users;
import com.example.backend.dto.UsersDto;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Service
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UsersPrincipal(users);
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Transactional
    public ResponseEntity<Map<String,String>> save(UsersDto usersDto) {
        Users existing = findByEmail(usersDto.getEmail());

        if (existing != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));        }

        Users users = new Users();
        users.setUsername(usersDto.getUsername());  
        users.setEmail(usersDto.getEmail());
        users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        usersRepository.save(users);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
}
