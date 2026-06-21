"use client";

import { useState, useEffect } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { Title } from "@/components/atoms/Title";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { TreasureButton } from "@/components/molecules/TreasureButton";
import { useAudio } from "@/components/providers/AudioProvider";
import { BGM } from "@/lib/audioPaths";

export default function TreasurePage() {
    const router = useRouter();
    const [message, setMessage] = useState<string>("");
    const [isOpen, setIsOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [isOpening, setIsOpening] = useState(false);
    const [error, setError] = useState<Error | null>(null);
    const { playBgm } = useAudio();
    
    useEffect(() => {
        playBgm(BGM.cave);
        return () => playBgm(BGM.explore);
    }, [playBgm]);

    useEffect(() => {
        const fetchTreasure = async () => {
            setIsLoading(true);
            try {
                const response = await apiGet("/treasure");
                setMessage(response.message);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            } finally {
                setIsLoading(false);
            }
        };
        fetchTreasure();
    }, []);

    const handleOpenTreasure = async () => {
        if (isOpen || isOpening || isLoading) return;

        setIsOpening(true);
        try {
            const response = await apiPost("/treasure/open");
            setMessage(response.message);
            setIsOpen(true);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsOpening(false);
        }
    };

    const displayMessage = isLoading ? "読み込み中…" : message || "宝箱を発見した";

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/洞窟.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col">
                <header className="px-4 pt-8 text-center sm:pt-10">
                    <Title>{displayMessage}</Title>
                </header>

                {error ? (
                    <ErrorAlert message={error.message} />
                ) : null}

                <div className="flex flex-1 items-center justify-center px-4 py-6">
                    <TreasureButton isOpen={isOpen} isOpening={isOpening} isLoading={isLoading} handleOpenTreasure={handleOpenTreasure} />
                </div>

                {isOpen ? (
                    <footer className="absolute bottom-6 left-4 z-20 sm:bottom-8 sm:left-6">
                        <MainButton onClick={() => router.push("/explore")} kind="back">戻る</MainButton>
                    </footer>
                ) : null}
            </div>
        </div>
    );
}
