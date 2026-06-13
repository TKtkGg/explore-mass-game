import { BattleChoice } from "@/type/types";

export const isPlayerFast = (playerSpd: number, enemySpd: number, playerChoice: BattleChoice, enemyChoice: BattleChoice) => {
    if (enemyChoice === BattleChoice.DEFEND) {
        return false;
    }
    if ((playerSpd >= enemySpd) || playerChoice === BattleChoice.DEFEND) {
        return true;
    }
    return false;
}