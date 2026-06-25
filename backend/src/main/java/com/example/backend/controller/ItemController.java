package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.backend.dto.item.ItemResponse;
import com.example.backend.service.ItemService;

@RestController
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ItemResponse showOwnedItems(@RequestHeader("X-Session-Id") String sessionId) {
        return this.itemService.showOwnedItems(sessionId);
    }
}
