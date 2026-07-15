package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}