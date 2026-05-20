package com.example.backend.service.gamestate.item;

import org.springframework.stereotype.Service;

@Service
public class ItemListState {
    private ItemState[] itemList;

    public ItemListState() {
        this.itemList = new ItemState[] {
            new ItemState("回復薬(小)", 20, 100, "HEAL"),
            new ItemState("回復薬(中)", 50, 300, "HEAL"),
            new ItemState("回復薬(大)", 100, 500, "HEAL"),
        };
    }

    public ItemState[] getItemList() {
        return this.itemList;
    }
    
}
