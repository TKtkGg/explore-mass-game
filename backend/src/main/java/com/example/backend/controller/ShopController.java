package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.shop.ShopRequest;
import com.example.backend.dto.shop.ShopResponse;
import com.example.backend.service.ShopService;

@RestController
public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public ShopResponse showShop(@RequestHeader("X-Session-Id") String sessionId) {
        return this.shopService.showShop(sessionId);
    }

    @PostMapping("/shop/buy")
    public ShopResponse buy(@RequestBody ShopRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.shopService.buy(request, sessionId);
    }
}
