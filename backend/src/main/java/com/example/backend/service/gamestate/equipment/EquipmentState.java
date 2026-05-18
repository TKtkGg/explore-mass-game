package com.example.backend.service.gamestate.equipment;

import com.example.backend.service.gamestate.Merchandise;
import java.util.Objects;

public class EquipmentState extends Merchandise {
    int atk;
    int chance;

    public EquipmentState(String name, int atk, int price, int chance) {
        super(name, price, "EQUIPMENT");
        this.atk = atk;
        this.chance = chance;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getChance() {
        return this.chance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EquipmentState)) return false;
        EquipmentState other = (EquipmentState) obj;
        return this.atk == other.atk && Objects.equals(this.getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), atk);
    }
}
