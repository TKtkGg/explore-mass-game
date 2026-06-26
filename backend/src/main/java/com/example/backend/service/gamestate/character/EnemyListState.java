package com.example.backend.service.gamestate.character;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EnemyListState {
    private List<EnemyState> enemyList = new ArrayList<>();
    
    public EnemyListState() {
        this.enemyList.add(new EnemyState("スライム", 1, 70, 70, 5, 5, 5, 20, 50, "/img/enemy/slime.png"));
        this.enemyList.add(new EnemyState("ゴブリン", 1, 90, 90, 8, 5, 8, 50, 80, "/img/enemy/goblin.png"));
    }

    public List<EnemyState> getEnemyList() {
        return this.enemyList;
    }
}
