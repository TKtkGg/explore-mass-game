package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.item.ItemResponse;
import com.example.backend.service.ItemService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ItemResponse showOwnedItems() {
        return this.itemService.showOwnedItems();
    }
}
