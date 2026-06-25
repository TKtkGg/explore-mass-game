"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { EquipmentState } from "@/type/types";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { EquipmentButton } from "@/components/molecules/EquipmentButton";
import { Tooltip } from "@/components/molecules/Tooltip";
import { useRequireSession } from "@/hooks/useRequireSession";
import { useRequireActiveGame } from "@/hooks/useRequireActiveGame";

interface EquipmentResponse {
    ownedEquipmentList: EquipmentState[];
    equipment: EquipmentState;
}

export default function EquipmentPage() {
    const router = useRouter();
    const [data, setData] = useState<EquipmentResponse | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const [isChanging, setIsChanging] = useState(false);
    const [hoveredEquipment, setHoveredEquipment] = useState<EquipmentState | null>(null);
    const [tooltipPos, setTooltipPos] = useState({ x: 0, y: 0 });
    useRequireSession();
    useRequireActiveGame();

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

    const updateTooltipPos = (event: React.MouseEvent<HTMLButtonElement>) => {
        setTooltipPos({ x: event.clientX, y: event.clientY });
    };

    const handleHoverStart = (
        equipment: EquipmentState,
        event: React.MouseEvent<HTMLButtonElement>
    ) => {
        setHoveredEquipment(equipment);
        updateTooltipPos(event);
    };

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/ステータス系.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col px-4 py-6 sm:px-8">
                {error ? <ErrorAlert message={error.message} /> : null}

                <main className="flex flex-1 items-start justify-start px-4 pt-6 pb-20 sm:px-8 sm:pt-8 sm:pb-24">
                    <div className="flex flex-wrap items-start justify-start gap-8 sm:gap-10">
                        {data?.ownedEquipmentList.map((equipment: EquipmentState) => (
                            <EquipmentButton
                                key={equipment.name}
                                equipment={equipment}
                                isEquipped={equipment.name === data?.equipment?.name}
                                onClick={changeEquipment}
                                onHoverStart={handleHoverStart}
                                onHoverMove={updateTooltipPos}
                                onHoverEnd={() => setHoveredEquipment(null)}
                                disabled={isChanging}
                            />
                        ))}
                    </div>
                </main>

                {hoveredEquipment ? (
                    <Tooltip 
                        title={hoveredEquipment.name}
                        lines={`ATK : ${hoveredEquipment.atk}`}
                        x={tooltipPos.x}
                        y={tooltipPos.y}
                    />
                ) : null}

                <footer className="absolute bottom-6 left-4 z-20 sm:bottom-15 sm:left-20">
                    <MainButton onClick={() => router.push("/status")} kind="back">戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}
