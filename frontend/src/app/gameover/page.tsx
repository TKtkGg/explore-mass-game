"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";

export default function GameOverPage() {
    const router = useRouter();
    const [error, setError] = useState<string | null>(null);
    const [score, setScore] = useState<number>(0);
    const [registerScore, setRegisterScore] = useState<boolean>(false);

    useEffect(() => {
        const fetchGameover = async () => {
            try {
                const response = await apiGet("/gameover");
                setScore(response.score);
            } catch (error: unknown) {
                if(error instanceof Error) {
                    setError(error.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        };
        fetchGameover();
    }, []);

    const handleRegisterScore = async () => {
        try {
            await apiPost("/score/register", { score: score });
            setRegisterScore(true);
        } catch (error: unknown) {
            if(error instanceof Error) {
                setError(error.message);
            } else {
                setError("通信に失敗しました。");
            }
        }
    };

    return (
        <div>
            <h1>GAME OVER</h1>
            {error && <p>{error}</p>}
            <p>Score: {score}</p>
            <button onClick={() => router.push("/")}>RESTART</button>
            <button onClick={() => handleRegisterScore()}>スコアを登録する</button>
            {registerScore && <p>スコアを登録しました。</p>}
        </div>
    );
}