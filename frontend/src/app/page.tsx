"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { apiPost } from "@/lib/apiClient";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { TwoRowTitle } from "@/components/atoms/TwoRowTitle";
import { Input } from "@/components/atoms/Input";
import { useAudio } from "@/components/providers/AudioProvider";
import { saveSessionId, clearSessionId } from "@/lib/session";

export default function Home() {
    const router = useRouter();
    const [name, setName] = useState("");
    const [error, setError] = useState<string | null>(null);
    const { unlockAudio } = useAudio();

    useEffect(() => {
        clearSessionId();
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
                style={{ backgroundImage: "url('/background/木の板.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col items-center px-4 py-8">
                <TwoRowTitle firstRow="LIMIT" secondRow="EXPLORE" />

                <div className="flex flex-1 flex-col items-center justify-center gap-10 sm:gap-12">
                    <Input placeholder="名前" value={name} onChange={(e) => setName(e.target.value)} />

                    <MainButton onClick={() => {
                        unlockAudio();
                        handleStart();
                    }} kind="start">START</MainButton>

                    {error ? <ErrorAlert message={error} /> : null}
                </div>
            </div>
        </div>
    );
}
