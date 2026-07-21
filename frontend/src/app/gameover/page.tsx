"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { TwoRowTitle } from "@/components/atoms/TwoRowTitle";
import { useAudio } from "@/components/providers/AudioProvider";
import { BGM } from "@/lib/audioPaths";
import { BACKGROUNDS } from "@/lib/imagePaths";
import { clearSessionId } from "@/lib/session";
import { useRequireSession } from "@/hooks/useRequireSession";
export default function GameOverPage() {
    const router = useRouter();
    const [error, setError] = useState<string | null>(null);
    const [score, setScore] = useState<number>(0);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [registerScore, setRegisterScore] = useState<boolean>(false);
    const { playBgm, stopBgm } = useAudio();
    useRequireSession();

    useEffect(() => {
        playBgm(BGM.gameover);
        return () => stopBgm();
    }, [playBgm, stopBgm]);

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

    useEffect(() => {
        const fetchGameover = async () => {
            try {
                const response = await apiGet("/gameover");
                setScore(response.score);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        };
        fetchGameover();
    }, []);

    const handleRegisterScore = async () => {
        try {
            await apiPost("/score/register");
            setRegisterScore(true);
            setError(null);
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
                <TwoRowTitle firstRow="GAME" secondRow="OVER" />

                <div className="flex flex-1 flex-col items-center justify-center gap-12 sm:gap-16">
                    <p className="text-3xl font-black text-white text-outline sm:text-4xl md:text-5xl">
                        SCORE : {score}
                    </p>

                    <div className="flex flex-col items-center gap-4">
                        {!registerScore ? (
                            <MainButton onClick={handleRegisterScore} disabled={!isLoggedIn}>ランキングに登録する</MainButton>
                        ) : (
                            <MainButton onClick={() => router.push("/ranking")}>ランキング</MainButton>
                        )}

                        <MainButton onClick={() => {
                            clearSessionId();
                            router.push("/");
                        }}>RESTART</MainButton>
                    </div>
                </div>

                {error ? <ErrorAlert message={error} /> : null}
            </div>
        </div>
    );
}
