package com.example.backend.dto.item;

import java.util.Map;

public class ItemResponse {
    private Map<String, Integer> ownedItems;

    public ItemResponse(Map<String, Integer> ownedItems) {
        this.ownedItems = ownedItems;
    }

    public Map<String, Integer> getOwnedItems() {
        return this.ownedItems;
    }
}
