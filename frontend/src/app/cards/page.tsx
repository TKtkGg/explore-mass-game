"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";
import { CardState } from "@/type/types";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { CardDisplay } from "@/components/molecules/CardDisplay";
import { Tooltip } from "@/components/molecules/Tooltip";
import { useRequireSession } from "@/hooks/useRequireSession";

interface CardsResponse {
    ownedCards: CardState[];
}

export default function CardsPage() {
    const router = useRouter();
    const [data, setData] = useState<CardsResponse | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const [hoveredCard, setHoveredCard] = useState<CardState | null>(null);
    const [tooltipPos, setTooltipPos] = useState({ x: 0, y: 0 });
    useRequireSession();

    useEffect(() => {
        const fetchCards = async () => {
            try {
                const response = await apiGet("/cards");
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
        fetchCards();
    }, []);

    const updateTooltipPos = (event: React.MouseEvent<HTMLDivElement>) => {
        setTooltipPos({ x: event.clientX, y: event.clientY });
    };

    const handleHoverStart = (
        card: CardState,
        event: React.MouseEvent<HTMLDivElement>
    ) => {
        setHoveredCard(card);
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
                        {data?.ownedCards.map((card: CardState) => (
                            <CardDisplay
                                key={card.name}
                                card={card}
                                onHoverStart={handleHoverStart}
                                onHoverMove={updateTooltipPos}
                                onHoverEnd={() => setHoveredCard(null)}
                            />
                        ))}
                    </div>
                </main>

                {hoveredCard ? (
                    <Tooltip 
                        title={hoveredCard.name} 
                        lines={hoveredCard.text} 
                        x={tooltipPos.x}
                        y={tooltipPos.y} 
                        wide={true} 
                    />
                ) : null}

                <footer className="absolute bottom-6 left-4 z-20 sm:bottom-15 sm:left-20">
                    <MainButton onClick={() => router.push("/status")} kind="back">戻る</MainButton>
                </footer>
            </div>
        </div>
    );
}
