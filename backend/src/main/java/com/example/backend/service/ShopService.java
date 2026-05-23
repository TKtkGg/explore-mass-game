package com.example.backend.service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.backend.dto.shop.ShopResponse;
import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.dto.shop.ShopRequest;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.item.ItemState;
import com.example.backend.service.gamestate.shop.Merchandise;
import com.example.backend.service.gamestate.shop.ShopState;
import com.example.backend.service.gamestate.item.ItemListState;

@Service
public class ShopService {
    private PlayerState playerState;
    private CardService cardService;
    private EquipmentService equipmentService;
    private ItemListState itemListState;
    private ShopState shopState;
    public ShopService(PlayerState playerState, CardService cardService, EquipmentService equipmentService, ItemListState itemListState, ShopState shopState) {
        this.playerState = playerState;
        this.cardService = cardService;
        this.equipmentService = equipmentService;
        this.itemListState = itemListState;
        this.shopState = shopState;
    }

    public void display() {
        List<Merchandise> lineup = new ArrayList<>();
        lineup.addAll(this.cardService.getUnownedCards());
        lineup.addAll(this.equipmentService.getUnownedEquipments());
        lineup.addAll(this.itemListState.getItemList());
        Collections.shuffle(lineup);
        List<Merchandise> display = new ArrayList<>();
        for(Merchandise merchandise : lineup) {
            display.add(merchandise);
            if(display.size() == 4) {
                break;
            }
        }
        this.shopState.setDisplay(display);
    }

    public ShopResponse showShop() {
        display();
        return new ShopResponse(this.playerState.getGold(), this.shopState.getDisplay(), "");
    }

    public ShopResponse buy(ShopRequest request) {
        Merchandise boughtItem = this.shopState.getDisplay().stream().filter(item -> item.getName().equals(request.getName())).findFirst().orElse(null);
        if(boughtItem == null) {
            return new ShopResponse(this.playerState.getGold(), this.shopState.getDisplay(), "商品が見つかりません");
        }
        if(this.playerState.getGold() < boughtItem.getPrice()) {
            return new ShopResponse(this.playerState.getGold(), this.shopState.getDisplay(), "ゴールドが足りません");
        }
        if(boughtItem.getType().equals("CARD")) {
            this.playerState.addCard((CardState) boughtItem);
        } else if (boughtItem.getType().equals("EQUIPMENT")) {
            this.playerState.addEquipment((EquipmentState) boughtItem);
        } else if (boughtItem.getType().equals("ITEM")) {
            this.playerState.addItem((ItemState) boughtItem, 1);
        }
        this.playerState.setGold(this.playerState.getGold() - boughtItem.getPrice());
        this.shopState.getDisplay().remove(boughtItem);
        return new ShopResponse(this.playerState.getGold(), this.shopState.getDisplay(), boughtItem.getName() + "を手に入れた！");
    }
}
