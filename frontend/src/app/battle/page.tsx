"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { BattleState, BattleChoice } from "@/type/types";
import { useRouter } from "next/navigation";

export default function BattlePage() {
    const [battleState, setBattleState] = useState<BattleState | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [itemOptions, setItemOptions] = useState<string[]>([]);
    const router = useRouter();
    useEffect(() => {
        const start = async () => {
            const response = await apiGet("/battle");
            setBattleState(response);
        }
        start();
    }, []);

    const handleChoice = async (choice: BattleChoice) => {
        if (choice === BattleChoice.ITEM) {
            setItemOptions(Object.keys(battleState?.playerState.ownedItems ?? {}));
        } else {
            setItemOptions([]);
            try {
                const response = await apiPost("/battle/action", { playerChoice: choice });
                setBattleState(response);
            } catch (error: unknown) {
                if(error instanceof Error) {
                    setError(error.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        }
    }
    const handleItemChoice = async (itemName: string) => {
        setItemOptions([]);
        try {
            const response = await apiPost("/battle/action", { playerChoice: BattleChoice.ITEM, itemName: itemName });
            setBattleState(response);
        } catch (error: unknown) {
            if(error instanceof Error) {
                setError(error.message);
            } else {
                setError("通信に失敗しました。");
            }
        }
    }
    return (
        <div>
            <h1>Battle</h1>
            {!battleState?.battleState.finished && (
                <>
                    <h1>{battleState?.playerState.name}({battleState?.playerState.level}LV) vs {battleState?.enemyState.name}({battleState?.enemyState.level}LV)</h1>
                    <h1>HP: {battleState?.playerState.hp} / {battleState?.playerState.maxHp}(player)</h1>
                    <h1>HP: {battleState?.enemyState.hp} / {battleState?.enemyState.maxHp}(enemy)</h1>
                    <h1>ATK: {battleState?.playerState.atk}(player) / {battleState?.enemyState.atk}(enemy)</h1>
                    <h1>DEF: {battleState?.playerState.def}(player) / {battleState?.enemyState.def}(enemy)</h1>
                    <h1>SPD: {battleState?.playerState.spd}(player) / {battleState?.enemyState.spd}(enemy)</h1>
                    {Object.values(BattleChoice).map((choice) => (
                        <div key={choice}>
                            <button onClick={() => handleChoice(choice)}>
                                {choice}
                            </button>
                        </div>
                    ))}
                    {itemOptions && (
                        <div>
                            {itemOptions.map((item) => (
                                <div key={item}>
                                    <button onClick={() => handleItemChoice(item)}>{item} ({battleState?.playerState.ownedItems[item]})</button>
                                </div>
                            ))}
                        </div>
                    )}
                </>
            )}
            <p>{battleState?.message}</p>
            {battleState?.message.includes("レベルアップ！") &&
                <>
                    <p>LV:{battleState?.playerState.level - 1} → {battleState?.playerState.level}</p>
                    <p>HP:{battleState?.playerState.maxHp - 10} → {battleState?.playerState.maxHp}</p>
                    <p>ATK:{battleState?.playerState.atk - 1} → {battleState?.playerState.atk}</p>
                    <p>DEF:{battleState?.playerState.def - 1} → {battleState?.playerState.def}</p>
                    <p>SPD:{battleState?.playerState.spd - 1} → {battleState?.playerState.spd}</p>
                </>
            }
            {error && <h1>{error}</h1>}
            {battleState?.battleState.finished &&
                <button onClick={() => router.push("/explore")}>BACK</button>
            }
        </div>
    );
}