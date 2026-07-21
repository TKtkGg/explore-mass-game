package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.Instant;

@Entity
@Table(name = "save_data")
public class SaveData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "save_data", nullable = false, columnDefinition = "TEXT")
    private String saveData;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public SaveData(Integer userId, String saveData, Instant updatedAt) {
        this.userId = userId;
        this.saveData = saveData;
        this.updatedAt = updatedAt;
    }

    protected SaveData() {}

    public Integer getUserId() {
        return userId;
    }

    public String getSaveData() {
        return saveData;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void setSaveData(String saveData) {
        this.saveData = saveData;
    }
    
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
