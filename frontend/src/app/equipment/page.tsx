"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { EquipmentState } from "@/type/types";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { EquipmentButton } from "@/components/molecules/EquipmentButton";

/** 背景画像（ステータス系.jpg）の枠バーと同系色 */
const FRAME_BAR_COLOR = "#8b6914";
const FRAME_BAR_WIDTH = "clamp(14px, 2.8vw, 28px)";

interface EquipmentResponse {
    ownedEquipmentList: EquipmentState[];
    equipment: EquipmentState;
}

export default function EquipmentPage() {
    const router = useRouter();
    const [data, setData] = useState<EquipmentResponse | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const [isChanging, setIsChanging] = useState(false);

    useEffect(() => {
        const fetchEquipment = async () => {
            try {
                const response = await apiGet("/equipment");
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
        fetchEquipment();
    }, []);

    const changeEquipment = async (name: string) => {
        if (isChanging || data?.equipment.name === name) return;

        setIsChanging(true);
        try {
            const response = await apiPost("/equipment/change", { name });
            setData(response);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsChanging(false);
        }
    };

    const equipped = data?.equipment;

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/ステータス系.jpg')" }}
                aria-hidden
            />

            <div
                className="relative z-10 flex min-h-[100dvh] flex-col"
                style={{ padding: "60px" }}
            >
                {error ? <ErrorAlert message={error.message} /> : null}

                {/* 右上: 背景枠と同色の棒で区切った詳細欄 */}
                <aside
                    className="absolute z-20 flex flex-col"
                    style={{
                        top: "0px",
                        right: "60px",
                    }}
                >
                    <div className="flex">
                        <div
                            style={{
                                width: FRAME_BAR_WIDTH,
                                backgroundColor: FRAME_BAR_COLOR,
                            }}
                            aria-hidden
                        />
                        <div className="min-w-[min(42vw,220px)] bg-black px-5 py-4 sm:min-w-[240px] sm:px-6 sm:py-5">
                            <p className="text-2xl font-black text-white text-outline sm:text-4xl">
                                {equipped?.name ?? "—"}
                            </p>
                            <p className="mt-2 text-xl font-black text-white text-outline sm:text-3xl">
                                ATK : {equipped?.atk ?? "—"}
                            </p>
                        </div>
                    </div>
                    <div
                        style={{
                            height: FRAME_BAR_WIDTH,
                            backgroundColor: FRAME_BAR_COLOR,
                        }}
                        aria-hidden
                    />
                </aside>
                <main className="flex flex-1 flex-col px-4 pt-6 pb-24 sm:px-8 sm:pt-8">
                    <div className="flex flex-1 flex-col gap-6 lg:flex-row lg:items-start lg:justify-between">
                        <div className="flex max-w-4xl flex-wrap items-center justify-center gap-8 sm:gap-10">
                            {data?.ownedEquipmentList.map((equipment: EquipmentState) => (
                                <EquipmentButton
                                    key={equipment.name}
                                    equipment={equipment}
                                    isEquipped={equipment.name === data?.equipment?.name}
                                    onClick={changeEquipment}
                                    disabled={isChanging}
                                />
                            ))}
                        </div>
                    </div>
                </main>

                <footer className="absolute bottom-6 left-4 z-20 sm:bottom-15 sm:left-20">
                    <MainButton onClick={() => router.push("/status")}>戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}
