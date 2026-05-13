package com.example.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.equipment.EquipmentRequest;
import com.example.backend.dto.shop.ShopResponse;
import com.example.backend.service.ShopService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public ShopResponse showShop() {
        return this.shopService.showShop();
    }

    @PostMapping("/shop/buyCard")
    public ShopResponse buyCard(@RequestBody CardRequest request) {
        return this.shopService.buyCard(request);
    }

    @PostMapping("/shop/buyEquipment")
    public ShopResponse buyEquipment(@RequestBody EquipmentRequest request) {
        return this.shopService.buyEquipment(request);
    }
}
