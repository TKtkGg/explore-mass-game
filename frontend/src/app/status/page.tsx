"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";
import Image from "next/image";
import { CardState, EquipmentState, StatusState } from "@/type/types";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { useAudio } from "@/components/providers/AudioProvider";
import { SFX } from "@/lib/audioPaths";
import { BACKGROUNDS, ICONS } from "@/lib/imagePaths";
import { useRequireSession } from "@/hooks/useRequireSession";
import { useRequireActiveGame } from "@/hooks/useRequireActiveGame";

export default function StatusPage() {
    const router = useRouter();
    const [data, setData] = useState<StatusState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const { playSfx } = useAudio();
    useRequireSession();
    useRequireActiveGame();

    useEffect(() => {
        const fetchStatus = async () => {
            try {
                const response = await apiGet("/status");
                setData(response);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            }
        };
        fetchStatus();
    }, []);

    const displayedEquipments = (() => {
        const current = data?.equipment;
        const owned = data?.ownedEquipmentList ?? [];
        if (!current) return owned.slice(0, 3);

        const result: EquipmentState[] = [current];
        for (const equipment of owned) {
            if (result.length >= 3) break;
            if (equipment.name !== current.name) {
                result.push(equipment);
            }
        }
        return result.slice(0, 3);
    })();

    const displayedCards = (data?.ownedCards ?? []).slice(0, 3);
    const displayedItem = Object.entries(data?.ownedItems ?? {})[0];

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: `url('${BACKGROUNDS.status}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col">
                {error ? <ErrorAlert message={error.message} /> : null}

                <main className="flex flex-1 items-start justify-center px-4 pt-4 pb-10 sm:px-8 sm:pt-6">
                    <div className="w-full max-w-7xl bg-black/90 px-6 py-8 text-white sm:px-10 sm:py-10">
                        <div className="grid grid-cols-1 gap-10 lg:grid-cols-[0.88fr_1.12fr] lg:gap-14">
                            <section className="text-4xl font-black leading-tight text-white text-outline sm:text-5xl">
                                <p>Lv.{data?.level ?? "-"} {data?.name ?? "プレイヤー"}</p>

                                <div className="h-8" />
                                <p>HP : {data?.hp ?? "-"} / {data?.maxHp ?? "-"}</p>
                                <p>ATK : {data?.totalAtk ?? "-"}</p>
                                <p>DEF : {data?.def ?? "-"}</p>
                                <p>SPD : {data?.spd ?? "-"}</p>

                                <div className="h-8" />
                                <p>EXP : {data?.exp ?? "-"} / {data?.nextLevelExp ?? "-"}</p>
                                <p>GOLD : {data?.gold ?? "-"}</p>

                                <div className="h-8" />
                                <button
                                    type="button"
                                    onClick={() => {
                                        router.push("/items");
                                        playSfx(SFX["button"]);
                                    }}
                                    className="mb-1 inline-flex items-end gap-1 text-4xl font-black text-white text-outline transition hover:text-neutral-200 sm:text-5xl"
                                >
                                    <span>ITEM</span>
                                    <span className="pb-1 text-sm font-bold sm:text-base">一覧を表示</span>
                                </button>
                                <p className="text-4xl sm:text-5xl">
                                    {displayedItem ? `${displayedItem[0]}(${displayedItem[1]})` : "なし"}
                                </p>
                            </section>

                            <section className="space-y-25">
                                <div>
                                    <button
                                        type="button"
                                        onClick={() => {
                                            router.push("/equipment");
                                            playSfx(SFX["button"]);
                                        }}
                                        className="mb-4 inline-flex items-end gap-1 text-5xl font-black text-white text-outline transition hover:text-neutral-200 sm:text-6xl"
                                    >
                                        <span>装備</span>
                                        <span className="pb-1 text-sm font-bold sm:text-base">一覧を表示</span>
                                    </button>
                                    <div className="grid grid-cols-3 gap-7">
                                        {displayedEquipments.map((equipment) => {
                                            const isEquipped = equipment.name === data?.equipment.name;
                                            return (
                                                <div key={equipment.name} className="flex flex-col items-center gap-3">
                                                    <Image
                                                        src={ICONS.weapon}
                                                        alt=""
                                                        width={112}
                                                        height={112}
                                                        className="h-auto w-24 object-contain sm:w-32"
                                                        unoptimized
                                                    />
                                                    <p
                                                        className={`text-center text-2xl font-black text-outline sm:text-2xl ${
                                                            isEquipped ? "text-yellow-300" : "text-white"
                                                        }`}
                                                    >
                                                        {equipment.name}
                                                    </p>
                                                </div>
                                            );
                                        })}
                                    </div>
                                </div>

                                <div>
                                    <button
                                        type="button"
                                        onClick={() => {
                                            router.push("/cards");
                                            playSfx(SFX["button"]);
                                        }}
                                        className="mb-4 inline-flex items-end gap-1 text-5xl font-black text-white text-outline transition hover:text-neutral-200 sm:text-6xl"
                                    >
                                        <span>カード</span>
                                        <span className="pb-1 text-sm font-bold sm:text-base">一覧を表示</span>
                                    </button>
                                    <div className="grid grid-cols-3 gap-7">
                                        {displayedCards.map((card: CardState) => (
                                            <div key={card.name} className="flex flex-col items-center gap-3">
                                                <Image
                                                    src={ICONS.card}
                                                    alt=""
                                                    width={112}
                                                    height={112}
                                                    className="h-auto w-24 object-contain sm:w-32"
                                                    unoptimized
                                                />
                                                <p className="text-center text-2xl font-black text-white text-outline sm:text-2xl">
                                                    {card.name}
                                                </p>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </main>

                <footer className="absolute bottom-6 left-1/2 z-20 -translate-x-1/2 sm:bottom-15 sm:left-20 sm:translate-x-0">
                    <MainButton onClick={() => router.push("/explore")} kind="back">戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}