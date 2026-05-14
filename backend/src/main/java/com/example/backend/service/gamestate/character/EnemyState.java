package com.example.backend.service.gamestate.character;

import org.springframework.stereotype.Service;

@Service
public class EnemyState extends CharacterState {
    public EnemyState() {
        super("test_enemy", 1, 100, 100, 10, 10, 10, 100, 100);
    }

    public void respawn() {
        this.hp = this.maxHp;
    }
}
