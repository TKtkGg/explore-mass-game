package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.dto.item.ItemResponse;

@Service
public class ItemService {
    private PlayerState playerState;
    public ItemService(PlayerState playerState) {
        this.playerState = playerState;
    }

    public ItemResponse showOwnedItems() {
        return new ItemResponse(this.playerState.getOwnedItems());
    }
}
