package com.example.backend.service.gamestate.shop;

public abstract class Merchandise {
    private String name;
    private int price;
    private String type;

    public Merchandise(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
