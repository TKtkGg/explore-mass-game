package com.example.backend.service.gamestate.character;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EnemyListState {
    private List<EnemyState> enemyList = new ArrayList<>();
    
    public EnemyListState() {
        this.enemyList.add(new EnemyState("スライム", 1, 70, 70, 5, 5, 5, 20, 50, "/img/enemy/slime.png", 1, 10));
        this.enemyList.add(new EnemyState("ゴブリン", 1, 90, 90, 8, 5, 8, 50, 80, "/img/enemy/goblin.png", 1, 10));
        this.enemyList.add(new EnemyState("ゾンビ", 1, 100, 100, 8, 4, 3, 35, 60, "/img/enemy/zombie.png", 1, 10));
        this.enemyList.add(new EnemyState("オーク", 1, 125, 125, 10, 8, 5, 75, 100, "/img/enemy/orc.png", 2, 6));
        this.enemyList.add(new EnemyState("ドクロ", 1, 65, 65, 10, 3, 11, 50, 60, "/img/enemy/headBone.png", 2, 8));
        this.enemyList.add(new EnemyState("ゴーレム", 1, 150, 150, 5, 15, 2, 80, 110, "/img/enemy/golem.png", 3, 5));
        this.enemyList.add(new EnemyState("ヒポグリフ", 1, 100, 100, 12, 9, 12, 100, 130, "/img/enemy/hippogriff.png", 4, 3));
        this.enemyList.add(new EnemyState("ミノタウロス", 1, 130, 130, 14, 8, 9, 105, 120, "/img/enemy/minotaur.png", 4, 3));
        this.enemyList.add(new EnemyState("ケルベロス", 1, 150, 150, 13, 10, 10, 130, 150, "/img/enemy/kerberos.png", 4, 3));
        this.enemyList.add(new EnemyState("ドラゴン", 1, 175, 175, 18, 12, 12, 170, 200, "/img/enemy/dragon.png", 5, 2));
    }

    public List<EnemyState> getEnemyList() {
        return this.enemyList;
    }
}
