"use client";

import { useRouter } from "next/navigation";

export default function GameOverPage() {
    const router = useRouter();

    return (
        <div>
            <h1>GAME OVER</h1>
            <button onClick={() => router.push("/")}>RESTART</button>
        </div>
    );
}