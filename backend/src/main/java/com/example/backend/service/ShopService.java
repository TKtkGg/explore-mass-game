package com.example.backend.service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.backend.dto.shop.ShopResponse;
import com.example.backend.dto.shop.ShopRequest;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.item.ItemState;
import com.example.backend.service.gamestate.shop.Merchandise;
import com.example.backend.service.gamestate.item.ItemListState;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class ShopService {
    private GameSessionManager gameSessionManager;
    private CardService cardService;
    private EquipmentService equipmentService;
    private ItemListState itemListState;
    public ShopService(GameSessionManager gameSessionManager, CardService cardService, EquipmentService equipmentService, ItemListState itemListState) {
        this.gameSessionManager = gameSessionManager;
        this.cardService = cardService;
        this.equipmentService = equipmentService;
        this.itemListState = itemListState;
    }

    public void display(GameSession gameSession) {
        List<Merchandise> lineup = new ArrayList<>();
        lineup.addAll(this.cardService.getUnownedCards(gameSession));
        lineup.addAll(this.equipmentService.getUnownedEquipments(gameSession));
        lineup.addAll(this.itemListState.getItemList());
        Collections.shuffle(lineup);
        List<Merchandise> display = new ArrayList<>();
        for(Merchandise merchandise : lineup) {
            display.add(merchandise);
            if(display.size() == 4) {
                break;
            }
        }
        gameSession.getShopState().setDisplay(display);
    }

    public ShopResponse showShop(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        display(gameSession);
        return new ShopResponse(gameSession.getPlayerState().getGold(), gameSession.getShopState().getDisplay(), "");
    }

    public ShopResponse buy(ShopRequest request, String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        Merchandise boughtItem = gameSession.getShopState().getDisplay().stream().filter(item -> item.getName().equals(request.getName())).findFirst().orElse(null);
        if(boughtItem == null) {
            return new ShopResponse(gameSession.getPlayerState().getGold(), gameSession.getShopState().getDisplay(), "商品が見つかりません");
        }
        if(gameSession.getPlayerState().getGold() < boughtItem.getPrice()) {
            return new ShopResponse(gameSession.getPlayerState().getGold(), gameSession.getShopState().getDisplay(), "ゴールドが足りません");
        }
        if(boughtItem.getType().equals("CARD")) {
            gameSession.getPlayerState().addCard((CardState) boughtItem);
        } else if (boughtItem.getType().equals("EQUIPMENT")) {
            gameSession.getPlayerState().addEquipment((EquipmentState) boughtItem);
        } else if (boughtItem.getType().equals("ITEM")) {
            gameSession.getPlayerState().addItem((ItemState) boughtItem, 1);
        }
        gameSession.getPlayerState().setGold(gameSession.getPlayerState().getGold() - boughtItem.getPrice());
        gameSession.getShopState().getDisplay().remove(boughtItem);
        return new ShopResponse(gameSession.getPlayerState().getGold(), gameSession.getShopState().getDisplay(), boughtItem.getName() + "を手に入れた！");
    }
}
