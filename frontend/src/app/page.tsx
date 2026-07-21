"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { apiPost, apiGet } from "@/lib/apiClient";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { TwoRowTitle } from "@/components/atoms/TwoRowTitle";
import { Input } from "@/components/atoms/Input";
import { useAudio } from "@/components/providers/AudioProvider";
import { saveSessionId, clearSessionId, clearGameFinished } from "@/lib/session";
import { BACKGROUNDS } from "@/lib/imagePaths";

export default function Home() {
    const router = useRouter();
    const [name, setName] = useState("");
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const { unlockAudio } = useAudio();

    useEffect(() => {
        clearSessionId();
        clearGameFinished();
    }, []);

    useEffect(() => {
        const fetchUser = async () => {
            const user = await apiGet("/auth/user");
            if (user.authenticated) {
                setIsLoggedIn(true);
            } else {
                setIsLoggedIn(false);
            }
        }
        fetchUser();
    }, []);
    
    const handleStart = async () => {
        if (name.length === 0) {
            setError("名前を入力してください。");
            return;
        }
        try {
            const response = await apiPost("/start", { name });
            saveSessionId(response.sessionId);
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
                style={{ backgroundImage: `url('${BACKGROUNDS.woodPlank}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col items-center px-4 py-8">
                <TwoRowTitle firstRow="LIMIT" secondRow="EXPLORE" />

                <div className="flex flex-1 w-full flex-col items-center justify-between pb-8 pt-10 sm:pb-12 sm:pt-14">
                    <div className="flex flex-col items-center gap-4 sm:gap-5">
                        <MainButton onClick={() => router.push("/login")}>LOGIN</MainButton>
                        <MainButton onClick={() => router.push("/signup")}>SIGNUP</MainButton>
                    </div>

                    <div className="flex flex-col items-center gap-8 sm:gap-10">
                        <Input placeholder="名前" value={name} onChange={(e) => setName(e.target.value)} />

                        <MainButton onClick={() => {
                            unlockAudio();
                            handleStart();
                        }} kind="start">START</MainButton>

                        {error ? <ErrorAlert message={error} /> : null}
                    </div>
                </div>

                <p className="pointer-events-none absolute bottom-4 right-4 text-base font-black tracking-wide text-white text-outline sm:bottom-6 sm:right-6 sm:text-xl md:text-2xl">
                    {isLoggedIn ? "ログイン中" : "ログインしていません"}
                </p>
            </div>
        </div>
    );
}
