"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { BattleState, BattleChoice } from "@/type/types";
import { useRouter } from "next/navigation";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { BattleMessageBox } from "@/components/molecules/BattleMessageBox";
import { BattleEnemyDisplay } from "@/components/molecules/BattleEnemyDisplay";
import { BattleCommandBox } from "@/components/molecules/BattleCommandBox";

export default function BattlePage() {
    const [battleState, setBattleState] = useState<BattleState | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [itemOptions, setItemOptions] = useState<string[]>([]);
    const [isActing, setIsActing] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const start = async () => {
            try {
                const response = await apiGet("/battle");
                setBattleState(response);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        };
        start();
    }, []);

    const handleChoice = async (choice: BattleChoice) => {
        if (isActing || battleState?.battleState.finished) return;

        if (choice === BattleChoice.ITEM) {
            setItemOptions(Object.keys(battleState?.playerState.ownedItems ?? {}));
            return;
        }

        setItemOptions([]);
        setIsActing(true);
        try {
            const response = await apiPost("/battle/action", { playerChoice: choice });
            setBattleState(response);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("通信に失敗しました。");
            }
        } finally {
            setIsActing(false);
        }
    };

    const handleItemChoice = async (itemName: string) => {
        if (isActing || battleState?.battleState.finished) return;

        setItemOptions([]);
        setIsActing(true);
        try {
            const response = await apiPost("/battle/action", {
                playerChoice: BattleChoice.ITEM,
                itemName,
            });
            setBattleState(response);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("通信に失敗しました。");
            }
        } finally {
            setIsActing(false);
        }
    };

    const finished = battleState?.battleState.finished === true;
    const playerAlive = (battleState?.playerState.hp ?? 0) > 0;
    const displayMessage = battleState?.message ?? "";

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/探索.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col">
                <BattleMessageBox message={displayMessage} />

                {error ? <ErrorAlert message={error} /> : null}

                <div className="flex flex-1 items-center justify-center pb-4 pt-24 sm:pt-28">
                    <BattleEnemyDisplay enemy={battleState?.enemyState} />
                </div>

                {!finished ? (
                    <BattleCommandBox
                        player={battleState?.playerState}
                        disabled={isActing || battleState === null}
                        onChoice={handleChoice}
                        itemOptions={itemOptions}
                        onItemChoice={handleItemChoice}
                        ownedItems={battleState?.playerState.ownedItems ?? {}}
                    />
                ) : null}

                {finished && playerAlive ? (
                    <footer className="absolute bottom-6 left-4 z-20 sm:bottom-8 sm:left-6">
                        <MainButton onClick={() => router.push("/explore")}>戻る</MainButton>
                    </footer>
                ) : null}

                {finished && !playerAlive ? (
                    <footer className="absolute bottom-6 left-4 z-20 sm:bottom-8 sm:left-6">
                        <MainButton onClick={() => router.push("/gameover")}>戻る</MainButton>
                    </footer>
                ) : null}
            </div>
        </div>
    );
}
