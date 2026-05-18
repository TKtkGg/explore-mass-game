package com.example.backend.dto.shop;

public class ShopRequest {
    private String name;

    public ShopRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
