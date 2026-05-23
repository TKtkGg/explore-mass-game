package com.example.backend.service.gamestate.card;

import java.util.Objects;

import com.example.backend.service.gamestate.shop.Merchandise;

public class CardState extends Merchandise {
    private String text;

    public CardState(String name, String text, int price) {
        super(name, price, "CARD");
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CardState)) return false;
        CardState other = (CardState) obj;
        return Objects.equals(this.getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}
