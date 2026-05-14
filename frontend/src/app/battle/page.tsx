"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { BattleState, BattleChoice } from "@/type/types";
import { useRouter } from "next/navigation";

export default function BattlePage() {
    const [battleState, setBattleState] = useState<BattleState | null>(null);
    const [error, setError] = useState<string | null>(null);
    const router = useRouter();
    useEffect(() => {
        const start = async () => {
            const response = await apiGet("/battle");
            setBattleState(response);
        }
        start();
    }, []);

    const handleChoice = async (choice: BattleChoice) => {
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
    return (
        <div>
            <h1>Battle</h1>
            {!battleState?.battleState.finished && (
                <>
                    <h1>{battleState?.playerState.name} vs {battleState?.enemyState.name}</h1>
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
                </>
            )}
            <p>{battleState?.message}</p>
            {error && <h1>{error}</h1>}
            {battleState?.battleState.finished &&
                <button onClick={() => router.push("/explore")}>BACK</button>
            }
        </div>
    );
}