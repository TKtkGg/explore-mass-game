"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { apiPost, apiGet } from "@/lib/apiClient";
import { useRouter } from "next/navigation";

/** 進行ボタン配置: 中央(上向き)・左下・右下 に routeOptions の 0,1,2 を対応 */
const ROUTE_SLOTS = [
    { positionClass: "left-1/2 top-[36%] -translate-x-1/2 -translate-y-1/2", rotationClass: "rotate-0" },
    { positionClass: "left-[10%] bottom-[20%] -translate-x-1/2", rotationClass: "-rotate-90" },
    { positionClass: "right-[10%] bottom-[20%] translate-x-1/2", rotationClass: "rotate-90" }
] as const;

const labelShadow =
    "[text-shadow:_2px_2px_0_#000,_-2px_-2px_0_#000,_2px_-2px_0_#000,_-2px_2px_0_#000,_0_2px_0_#000,_0_-2px_0_#000,_2px_0_0_#000,_-2px_0_0_#000]";

export default function ExplorePage() {
    const [remainingSteps, setRemainingSteps] = useState(25);
    const [stopped, setStopped] = useState(false);
    const [error, setError] = useState<Error | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [routeOptions, setRouteOptions] = useState<string[]>([]);
    const [message, setMessage] = useState("");
    const router = useRouter();

    useEffect(() => {
        const start = async () => {
            try {
                const response = await apiGet("/move/status");
                setRemainingSteps(response.remainingSteps);
                setStopped(response.stopped);
                setRouteOptions(response.routeOptions);
                setMessage(response.message);
                setError(null);
            } catch (error: unknown) {
                if (error instanceof Error) {
                setError(error);
                } else {
                setError(new Error("通信に失敗しました。"));
                }
            }
        };
        start();
    }, []);

    useEffect(() => {
        if (remainingSteps === 0) {
            router.push("/gameover");
        }
    }, [remainingSteps, router]);

    const handleMove = async (routeType: string) => {
        if (isLoading) return;
        setIsLoading(true);

        try {
            let response;
            if (routeType === "REST") {
                response = await apiPost("/move/rest", { routeType: routeType });
            } else if (routeType === "TREASURE") {
                response = await apiPost("/move/treasure", { routeType: routeType });
            } else if (routeType === "CARD") {
                response = await apiPost("/move", { routeType: routeType });
                router.push("/card");
            } else if (routeType === "SHOP") {
                response = await apiPost("/move", { routeType: routeType });
                router.push("/shop");
            } else if (routeType === "BATTLE") {
                response = await apiPost("/move", { routeType: routeType });
                router.push("/battle");
            } else {
                response = await apiPost("/move", { routeType: routeType });
            }
            setRemainingSteps(response.remainingSteps);
            setStopped(response.stopped);
            setRouteOptions(response.routeOptions);
            setMessage(response.message);
            setError(null);
        } catch (error: unknown) {
            if (error instanceof Error) {
                setError(error);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsLoading(false);
        }
    };

    const disabled = isLoading || stopped;

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            {/* 背景 */}
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/探索.jpg')" }}
                aria-hidden
            />

                {/* コンテンツ */}
                <div className="relative z-10 flex min-h-[100dvh] flex-col">
                    {/* 上段: RESTART + 残りマス */}
                    <div className="flex items-start justify-between px-4 pt-3 sm:px-6">
                        <button
                            type="button"
                            onClick={() => router.push("/")}
                            disabled={isLoading}
                            className="rounded-md border-2 border-black bg-white/90 px-3 py-1.5 text-xs font-bold text-neutral-900 shadow-[2px_2px_0_#000] transition hover:bg-white disabled:opacity-50 sm:text-sm"
                        >
                            RESTART
                        </button>
                    <div className="rounded-md border-2 border-black bg-white/90 px-3 py-1.5 text-xs font-bold tabular-nums text-neutral-900 shadow-[2px_2px_0_#000] sm:text-sm">
                        残り {remainingSteps}
                    </div>
                </div>

                {/* タイトル */}
                <header className="px-4 pt-2 text-center sm:pt-4">
                    <h1
                        className={`font-black text-white sm:tracking-[0.08em] ${labelShadow} text-4xl sm:text-6xl md:text-7xl`}
                    >
                        EXPLORE
                    </h1>
                    {message ? (
                        <p
                        className={`mx-auto mt-2 max-w-md rounded-md border-2 border-black bg-black/55 px-3 py-2 text-sm font-bold text-white ${labelShadow} sm:text-base`}
                        >
                        {message}
                        </p>
                    ) : null}
                </header>

                {/* エラー */}
                {error ? (
                    <p
                        className="mx-4 mt-2 rounded-md border-2 border-red-700 bg-red-950/90 px-3 py-2 text-center text-sm font-bold text-red-100"
                        role="alert"
                    >
                        {error.message}
                    </p>
                ) : null}

                {stopped ? (
                    <p className="mx-4 mt-2 text-center text-sm font-bold text-amber-200 [text-shadow:0_1px_2px_#000]">
                        マスが尽きました。まもなくゲームオーバーへ…
                    </p>
                ) : null}

                {/* 矢印エリア */}
                <div className="relative flex-1 min-h-[280px] sm:min-h-[360px]">
                    {ROUTE_SLOTS.map((slot, index) => {
                        const option = routeOptions[index];
                        if (!option) return null;
                        return (
                        <div
                            key={`${option}-${index}`}
                            className={`absolute flex flex-col items-center gap-2 sm:gap-3 ${slot.positionClass}`}
                        >
                            <button
                            type="button"
                            onClick={() => handleMove(option)}
                            disabled={disabled}
                            className="group flex flex-col items-center outline-none transition cursor-pointer disabled:opacity-40 disabled:grayscale"
                            aria-label={`${option}へ進む`}
                            >
                            <span className="transition group-active:translate-y-0.5 group-active:shadow-none sm:p-2">
                                <Image
                                src="/img/進行ボタン.png"
                                alt=""
                                width={150}
                                height={150}
                                className={`h-16 w-16 object-contain drop-shadow sm:h-44 sm:w-44 ${slot.rotationClass}`}
                                unoptimized
                                />
                            </span>
                            <span
                                className={`max-w-[28vw] truncate text-center text-sm font-black uppercase text-white sm:max-w-[140px] sm:text-lg ${labelShadow}`}
                            >
                                {option}
                            </span>
                            </button>
                        </div>
                        );
                    })}
                </div>

                {/* ローディング */}
                {isLoading ? (
                    <p className="pb-2 text-center text-xs font-bold text-white [text-shadow:0_1px_2px_#000]">
                        送信中…
                    </p>
                ) : null}

                {/* STATUS（下部固定風） */}
                <footer className="mt-auto flex justify-center px-4 pb-6 pt-4 sm:pb-8">
                <button
                    type="button"
                    onClick={() => router.push("/status")}
                    className={`min-w-[200px] rounded-md border-2 border-black bg-white px-8 py-3 text-lg font-black tracking-widest text-neutral-900 shadow-[4px_4px_0_#000] transition cursor-pointer hover:bg-neutral-100 active:translate-y-0.5 active:shadow-none sm:min-w-[240px] sm:text-xl`}
                >
                    STATUS
                </button>
                </footer>
            </div>
        </div>
    );
}
