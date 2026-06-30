"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { apiGet } from "@/lib/apiClient";
import { RankingResponse } from "@/type/types";
import { Title } from "@/components/atoms/Title";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { clearSessionId } from "@/lib/session";
import { BACKGROUNDS } from "@/lib/imagePaths";
import { useRequireSession } from "@/hooks/useRequireSession";
/** 6行まで表示し、7人目以降は箱内スクロール */
const VISIBLE_ROWS_BEFORE_SCROLL = 8;
const ROW_HEIGHT_REM = 3.5;

function getRankColorClass(rank: number): string {
    if (rank === 1) return "text-yellow-500";
    if (rank === 2) return "text-neutral-400";
    if (rank === 3) return "text-amber-700";
    return "text-neutral-900";
}

export default function RankingPage() {
    const router = useRouter();
    const [ranking, setRanking] = useState<RankingResponse[]>([]);
    const [playerName, setPlayerName] = useState<string | null>(null);
    const [error, setError] = useState<string | null>(null);
    useRequireSession();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const rankingResponse = await apiGet("/ranking");
                setRanking(rankingResponse);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }

            try {
                const statusResponse = await apiGet("/status");
                setPlayerName(statusResponse.name ?? null);
            } catch {
                setPlayerName(null);
            }
        };
        fetchData();
    }, []);

    const shouldScroll = ranking.length > VISIBLE_ROWS_BEFORE_SCROLL;
    const scrollAreaMaxHeight = `${VISIBLE_ROWS_BEFORE_SCROLL * ROW_HEIGHT_REM}rem`;

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: `url('${BACKGROUNDS.woodPlank}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col px-4 py-8 sm:px-8">
                <header className="pt-[4vh] text-center sm:pt-[6vh]">
                    <Title>RANKING</Title>
                </header>

                {error ? <ErrorAlert message={error} /> : null}

                <main className="flex min-h-0 flex-1 flex-col items-center py-6">
                    <div className="w-full max-w-4xl min-h-[400px] shrink-0 overflow-hidden border-2 border-black bg-white">
                        <div
                            className="overflow-y-auto"
                            style={
                                shouldScroll
                                    ? { maxHeight: scrollAreaMaxHeight }
                                    : undefined
                            }
                        >
                            {ranking.length === 0 ? (
                                <p className="px-4 py-8 text-center font-bold text-neutral-600">
                                    ランキングデータがありません
                                </p>
                            ) : (
                                ranking.map((entry, index) => {
                                    const rank = index + 1;
                                    const isSelf =
                                        playerName !== null &&
                                        entry.playerName === playerName;
                                    return (
                                        <div
                                            key={`${entry.playerName}-${entry.playedAt}-${index}`}
                                            className="flex min-h-14 shrink-0 border-b border-neutral-400 last:border-b-0 sm:min-h-16"
                                        >
                                            <div
                                                className={`flex w-20 shrink-0 items-center justify-center border-r border-neutral-400 text-lg font-black sm:w-24 sm:text-xl ${getRankColorClass(rank)}`}
                                            >
                                                {rank}位
                                            </div>
                                            <div
                                                className={`flex flex-1 items-center px-4 text-base font-bold sm:text-lg ${
                                                    isSelf
                                                        ? "text-blue-600"
                                                        : "text-neutral-900"
                                                }`}
                                            >
                                                {entry.playerName}({entry.level}Lv) :{" "}
                                                {entry.score}
                                            </div>
                                        </div>
                                    );
                                })
                            )}
                        </div>
                    </div>
                </main>

                <footer className="flex justify-center pb-2 sm:justify-start sm:pb-4">
                    <MainButton onClick={() => {
                        clearSessionId();
                        router.push("/");
                    }} kind="back">戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}
