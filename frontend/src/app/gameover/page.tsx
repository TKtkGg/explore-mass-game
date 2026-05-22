"use client";

import { useRouter } from "next/navigation";
import { apiPost } from "@/lib/apiClient";
import { useState } from "react";

export default function GameOverPage() {
    const router = useRouter();
    const [error, setError] = useState<string | null>(null);
    const handleRestart = async () => {
        try {
            await apiPost("/reset");
        } catch (error: unknown) {
            if(error instanceof Error) {
                setError(error.message);
            } else {
                setError("通信に失敗しました。");
            }
        }
        router.push("/explore");
    };

    return (
        <div>
            <h1>GAME OVER</h1>
            <button onClick={() => handleRestart()}>RESTART</button>
            {error && <p>{error}</p>}
        </div>
    );
}