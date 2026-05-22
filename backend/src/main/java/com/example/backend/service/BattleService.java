package com.example.backend.service;

import java.util.Random;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.battle.BattleResponse;
import com.example.backend.service.gamestate.BattleState;
import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.character.CharacterState;
import com.example.backend.service.gamestate.character.EnemyState;
import com.example.backend.service.gamestate.character.EnemyListState;
import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.dto.battle.BattleRequest;
import com.example.backend.domain.BattleChoice;
import com.example.backend.service.gamestate.item.ItemState;
import com.example.backend.service.gamestate.item.ItemListState;

@Service
public class BattleService {
    private Random rand = new Random();
    private PlayerState playerState;
    private EnemyState enemyState;
    private List<EnemyState> enemyList;
    private BattleState battleState;
    private ItemListState itemListState;
    private MoveService moveService;
    public BattleService(PlayerState playerState, EnemyListState enemyListState, BattleState battleState, ItemListState itemListState, MoveService moveService) {
        this.playerState = playerState;
        this.enemyList = enemyListState.getEnemyList();
        this.battleState = battleState;
        this.itemListState = itemListState;
        this.moveService = moveService;
    }

    public BattleResponse battleStart() {
        this.battleState.reset();
        EnemyState template = this.enemyList.get(rand.nextInt(this.enemyList.size()));
        this.enemyState = new EnemyState(template.getName(), template.getLevel(), template.getMaxHp(), template.getHp(), template.getAtk(), template.getDef(), template.getSpd(), template.getExp(), template.getGold());
        this.enemyState.adjustLevel(this.playerState);
        this.enemyState.respawn();
        return new BattleResponse("Battle started", this.playerState, this.enemyState, this.battleState);
    }

    public BattleResponse battle(BattleRequest request) {
        String message = "";
        this.battleState.setPlayerChoice(request.getPlayerChoice());
        this.battleState.setEnemyChoice(getRandomEnemyChoice());
        if(isPlayerFast()) {
            message = playerAction(request);
            if(this.enemyState.isAlive() || this.playerState.getIsRun()) {
                message += enemyAction();
            }
        } else {
            message = enemyAction();
            if(this.playerState.isAlive()) {
                message += playerAction(request);
            }
        }
        this.enemyState.setDefend(false);
        this.playerState.setDefend(false);
        this.battleState.setCurrentTurns(this.battleState.getCurrentTurns() + 1);
        return new BattleResponse(message, this.playerState, this.enemyState, this.battleState);
    }

    public BattleChoice getRandomEnemyChoice() {
        BattleChoice[] choices = BattleChoice.values();
        return choices[rand.nextInt(choices.length)];
    }

    public boolean isPlayerFast() {
        if(this.battleState.getEnemyChoice() == BattleChoice.DEFEND) {
            return false;
        }
        if((this.playerState.getSpd() >= this.enemyState.getSpd()) || this.battleState.getPlayerChoice() == BattleChoice.DEFEND) {
            return true;
        }
        return false;
    }

    public String playerAction(BattleRequest request){
        if(this.battleState.getPlayerChoice() == BattleChoice.ATTACK) {
            return attack(this.playerState, this.enemyState);
        } else if(this.battleState.getPlayerChoice() == BattleChoice.DEFEND) {
            return defend(this.playerState);
        } else if(this.battleState.getPlayerChoice() == BattleChoice.RUN) {
            return run();
        } else if(this.battleState.getPlayerChoice() == BattleChoice.ITEM) {
            return item(request.getItemName());
        } else {
            return attack(this.playerState, this.enemyState);
        }
    }

    public String enemyAction(){
        if(this.battleState.getEnemyChoice() == BattleChoice.ATTACK) {
            return attack(this.enemyState, this.playerState);
        } else if(this.battleState.getEnemyChoice() == BattleChoice.DEFEND) {
            return defend(this.enemyState);
        } else {
            return attack(this.enemyState, this.playerState);
        }
    }

    public String attack(CharacterState attackerState, CharacterState targetState){
        String message = "";
        double min = 0.8;
		double max = 1.3;
		double randomValue = Math.random() * (max - min) + min;
		int defendMultiplier = targetState.getDefend() ? 1 : 3;
		int damage = (int) ((attackerState.getAtk() - targetState.getDef() / defendMultiplier) * 2 * randomValue); 
        if(attackerState instanceof PlayerState) {
            damage = applyCards(damage);
        }
        if(damage < 0) {
            damage = 0;
        }
        targetState.setHp(targetState.getHp() - damage);
        if(targetState.getHp() < 0) {
            targetState.setHp(0);
            message = result(attackerState.getName());
        }
        return attackerState.getName() + "の攻撃！" + damage + "ダメージ！" + message;
    }

    public String defend(CharacterState defenderState){
        defenderState.setDefend(true);
        return defenderState.getName() + "は防御した！";
    }

    public String run(){
        this.playerState.setIsRun(true);
        return result("escape");
    }

    public String item(String itemName){
        ItemState item = this.itemListState.getItemList().stream().filter(i -> i.getName().equals(itemName)).findFirst().orElse(null);
        if(item == null || this.playerState.getOwnedItems().get(itemName) <= 0) {
            return "そのアイテムは持っていません。";
        }

        if (item.getEffectType().equals("HEAL")) {
            this.playerState.Heal(item.getAmount());
            this.playerState.removeItem(item, 1);
        }
        return this.playerState.getName() + "は" + itemName + "を使用した！";
    }

    public String result(String winnerName) {
        this.battleState.setFinished(true);
        if(winnerName != null && winnerName.equals(this.playerState.getName())) {
            String message = this.playerState.calcExp(this.enemyState.getExp());
            this.playerState.setGold(this.playerState.getGold() + this.enemyState.getGold());
            return winnerName + "の勝利！ +" + this.enemyState.getExp() + "EXP +" + this.enemyState.getGold() + "Gold " + message;
        } else if(winnerName != null && winnerName.equals(this.enemyState.getName())) {
            return this.playerState.getName() + "の敗北！";
        } else {
            return "逃げた！";
        }
    }

    public int applyCards(int damage) {
        for(CardState card : this.playerState.getOwnedCards()) {
            if(card.getName().equals("スライムキラー") && this.enemyState.getName().contains("スライム")) {
                damage = (int) (damage * 1.5);
            }
            if(card.getName().equals("ゴブリンキラー") && this.enemyState.getName().contains("ゴブリン")) {
                damage = (int) (damage * 1.5);
            }
        }

        return damage;
    }
}