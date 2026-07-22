package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.SaveData;

public interface SaveDataRepository extends JpaRepository<SaveData, Long> {
    SaveData findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
}
