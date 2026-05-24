"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { apiGet } from "@/lib/apiClient";
import { RankingResponse } from "@/type/types";

export default function RankingPage() {
    const router = useRouter();
    const [ranking, setRanking] = useState<RankingResponse[]>([]);
    const [error, setError] = useState<string | null>(null);
    
    useEffect(() => {
        const fetchRanking = async () => {
            try {
                const response = await apiGet("/ranking");
                setRanking(response);
            } catch (error: unknown) {
                if(error instanceof Error) {
                    setError(error.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        };
        fetchRanking();
    }, []);

    return (
        <div>
            <h1>ランキング</h1>
            {error && <p>{error}</p>}
            <ul>
                {ranking.map((ranking, index) => (
                    <li key={index}>{index + 1}位: {ranking.playerName}: {ranking.score} (レベル: {ranking.level})</li>
                ))}
            </ul>
            <button onClick={() => router.push("/")}>BACK</button>
        </div>
    );
}