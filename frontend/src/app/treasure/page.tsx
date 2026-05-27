"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { Title } from "@/components/atoms/Title";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";

export default function TreasurePage() {
    const router = useRouter();
    const [message, setMessage] = useState<string>("");
    const [isOpen, setIsOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [isOpening, setIsOpening] = useState(false);
    const [error, setError] = useState<Error | null>(null);

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
                    <button
                        type="button"
                        onClick={handleOpenTreasure}
                        disabled={isOpen || isOpening || isLoading}
                        className="group outline-none transition cursor-pointer disabled:cursor-default"
                        aria-label={isOpen ? "開いた宝箱" : "宝箱を開ける"}
                    >
                        <span className="block transition group-enabled:group-active:translate-y-1 group-enabled:group-active:scale-[0.98] group-disabled:opacity-90">
                            <Image
                                src={isOpen ? "/img/開いた宝箱.png" : "/img/閉じた宝箱.png"}
                                alt=""
                                width={400}
                                height={320}
                                className="h-auto w-[min(72vw,360px)] max-w-full object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.55)] sm:w-[min(56vw,420px)]"
                                unoptimized
                                priority
                            />
                        </span>
                    </button>
                </div>

                {isOpening ? (
                    <p className="pb-2 text-center text-xs font-bold text-white text-outline">
                        開封中…
                    </p>
                ) : null}

                {isOpen ? (
                    <footer className="absolute bottom-6 left-4 z-20 sm:bottom-8 sm:left-6">
                        <MainButton onClick={() => router.push("/explore")}>戻る</MainButton>
                    </footer>
                ) : null}
            </div>
        </div>
    );
}
