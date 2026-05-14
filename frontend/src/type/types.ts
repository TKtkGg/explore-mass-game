export type CardState = {
    name: string;
    text: string;
    price: number;
}

export type EquipmentState = {
    name: string;
    atk: number;
    price: number;
    chance: number;
}

export type ShopState = {
    gold: number;
    unownedCards: CardState[];
    unownedEquipments: EquipmentState[];
    message: string;
}

export type CharacterState = {
    name: string;
    level: number;
    maxHp: number;
    hp: number;
    atk: number;
    totalAtk: number;
    def: number;
    spd: number;
    exp: number;
    gold: number;
}

export type StatusState = {
    name: string;
    level: number;
    maxHp: number;
    hp: number;
    atk: number;
    totalAtk: number;
    def: number;
    spd: number;
    exp: number;
    gold: number;
    equipment: EquipmentState;
    ownedEquipmentList: EquipmentState[];
    ownedCards: CardState[];
}

export enum BattleChoice {
    ATTACK = "ATTACK",
    DEFEND = "DEFEND",
    RUN = "RUN",
}

export type BattleState = {
    message: string;
    playerState: StatusState;
    enemyState: CharacterState;
    battleState: {
        currentTurns: number;
        damage: number;
        finished: boolean;
        playerChoice: BattleChoice;
        enemyChoice: BattleChoice;
    };
}