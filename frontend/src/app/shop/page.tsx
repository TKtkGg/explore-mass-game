"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { MerchandiseState, ShopState } from "@/type/types";
import { Title } from "@/components/atoms/Title";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { ShopItemButton } from "@/components/molecules/ShopItemButton";
import { useAudio } from "@/components/providers/AudioProvider";
import { BGM, SFX } from "@/lib/audioPaths";
import { BACKGROUNDS } from "@/lib/imagePaths";
import { useRequireSession } from "@/hooks/useRequireSession";
import { useRequireActiveGame } from "@/hooks/useRequireActiveGame";

export default function ShopPage() {
    const router = useRouter();
    const [shop, setShop] = useState<ShopState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const [isBuying, setIsBuying] = useState(false);
    const { playBgm, playSfx } = useAudio();
    useRequireSession();
    useRequireActiveGame();

    useEffect(() => {
        playBgm(BGM.shop);
        return () => playBgm(BGM.explore);
    }, [playBgm]);

    useEffect(() => {
        const fetchShop = async () => {
            try {
                const response = await apiGet("/shop");
                setShop(response);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            }
        };
        fetchShop();
    }, []);

    const handleBuyMerchandise = async (merchandise: MerchandiseState) => {
        if (isBuying) return;
        setIsBuying(true);
        try {
            const response = await apiPost("/shop/buy", { name: merchandise.name });
            setShop(response);
            if (response.message?.includes("手に入れた！")) {
                playSfx(SFX.buy);
            }
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsBuying(false);
        }
    };

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-center bg-no-repeat"
                style={{ backgroundImage: `url('${BACKGROUNDS.shop}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col">
                <div className="absolute left-4 top-4 z-20 sm:left-6 sm:top-6">
                    <p className="text-sm font-bold text-white text-outline sm:text-base">所持金</p>
                    <p className="text-3xl font-black tabular-nums text-white text-outline sm:text-4xl md:text-5xl">
                        {shop !== null ? `${shop.gold}G` : "—"}
                    </p>
                </div>

                <header className="px-4 pt-8 text-center sm:pt-10">
                    <Title>SHOP</Title>
                    {shop?.message ? (
                        <p className="mx-auto mt-2 max-w-md rounded-md border-2 border-black bg-black/55 px-3 py-2 text-sm font-bold text-white text-outline absolute left-1/2 top-1/5 -translate-x-1/2 sm:text-base">
                            {shop.message}
                        </p>
                    ) : null}
                </header>

                {error ? <ErrorAlert message={error.message} /> : null}
                <div className="grid flex-1 grid-cols-2 place-content-center gap-x-4 gap-y-8 px-4 sm:flex sm:items-center sm:justify-evenly sm:gap-0 sm:px-0">
                    {shop?.display.map((merchandise: MerchandiseState) => (
                        <ShopItemButton
                            key={merchandise.name}
                            merchandise={merchandise}
                            onClick={handleBuyMerchandise}
                            disabled={isBuying}
                        />
                    ))}
                </div>

                <footer className="absolute bottom-6 left-1/2 z-20 -translate-x-1/2 sm:bottom-8 sm:left-6 sm:translate-x-0">
                    <MainButton onClick={() => router.push("/explore")} kind="back">戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}
