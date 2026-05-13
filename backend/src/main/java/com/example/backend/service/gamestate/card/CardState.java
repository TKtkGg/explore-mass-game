package com.example.backend.service.gamestate.card;

import java.util.Objects;

public class CardState {
    private String name;
    private String text;
    private int price;

    public CardState(String name, String text, int price) {
        this.name = name;
        this.text = text;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CardState)) return false;
        CardState other = (CardState) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
