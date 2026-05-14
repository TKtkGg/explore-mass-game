package com.example.backend.service.gamestate.character;

import org.springframework.stereotype.Service;

@Service
public class EnemyState extends CharacterState {
    public EnemyState(String name, int level, int maxHp, int hp, int atk, int def, int spd, int exp, int gold) {
        super(name, level, maxHp, hp, atk, def, spd, exp, gold);
    }

    public void respawn() {
        this.hp = this.maxHp;
    }
}
