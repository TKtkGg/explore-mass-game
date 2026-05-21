package com.example.backend.service.gamestate.item;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ItemListState {
    private List<ItemState> itemList;

    public ItemListState() {
        this.itemList = new ArrayList<ItemState>();
        this.itemList.add(new ItemState("回復薬(小)", 20, 100, "HEAL"));
        this.itemList.add(new ItemState("回復薬(中)", 50, 300, "HEAL"));
        this.itemList.add(new ItemState("回復薬(大)", 100, 500, "HEAL"));
    }

    public List<ItemState> getItemList() {
        return this.itemList;
    }
    
}
