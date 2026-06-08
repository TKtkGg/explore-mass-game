"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiPost } from "@/lib/apiClient";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";

export default function Home() {
    const router = useRouter();
    const [name, setName] = useState("");
    const [error, setError] = useState<string | null>(null);

    const handleStart = async () => {
        if (name.length === 0) {
            setError("名前を入力してください。");
            return;
        }
        try {
            await apiPost("/start", { name });
            router.push("/explore");
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("通信に失敗しました。");
            }
        }
    };

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/木の板.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col items-center px-4 py-8">
                <header className="pt-[6vh] text-center sm:pt-[8vh]">
                    <h1 className="font-black leading-tight text-white text-outline text-5xl sm:text-7xl md:text-8xl">
                        <span className="block">LIMIT</span>
                        <span className="block">EXPLORE</span>
                    </h1>
                </header>

                <div className="flex flex-1 flex-col items-center justify-center gap-10 sm:gap-12">
                    <input
                        type="text"
                        placeholder="名前"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="w-full max-w-lg border-2 border-black bg-white px-4 py-3 text-lg font-bold text-neutral-900 placeholder:text-neutral-400 focus:outline-none focus:ring-2 focus:ring-neutral-400 sm:py-4 sm:text-xl"
                    />

                    <MainButton onClick={handleStart}>START</MainButton>

                    {error ? <ErrorAlert message={error} /> : null}
                </div>
            </div>
        </div>
    );
}
