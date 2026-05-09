package com.example.backend.dto.status;

public class EquipmentRequest {
    private String name;

    public EquipmentRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
