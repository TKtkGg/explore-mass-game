"use client";

import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { ItemDisplay, OwnedItemDisplay } from "@/components/molecules/ItemDisplay";
import { Tooltip } from "@/components/molecules/Tooltip";
import { getItemHealAmount } from "@/lib/itemHealAmount";
import { BACKGROUNDS } from "@/lib/imagePaths";
import { useRequireSession } from "@/hooks/useRequireSession";
import { useRequireActiveGame } from "@/hooks/useRequireActiveGame";

interface ItemResponse {
    ownedItems: {
        [key: string]: number;
    };
}

export default function ItemsPage() {
    const router = useRouter();
    const [items, setItems] = useState<OwnedItemDisplay[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [hoveredItem, setHoveredItem] = useState<OwnedItemDisplay | null>(null);
    const [tooltipPos, setTooltipPos] = useState({ x: 0, y: 0 });
    useRequireSession();
    useRequireActiveGame();

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response: ItemResponse = await apiGet("/items");
                const ownedItems = Object.entries(response.ownedItems ?? {}).map(
                    ([name, count]) => ({ name, count })
                );
                setItems(ownedItems);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("通信に失敗しました。");
                }
            }
        };
        fetchItems();
    }, []);

    const updateTooltipPos = (event: React.MouseEvent<HTMLDivElement>) => {
        setTooltipPos({ x: event.clientX, y: event.clientY });
    };

    const handleHoverStart = (
        item: OwnedItemDisplay,
        event: React.MouseEvent<HTMLDivElement>
    ) => {
        setHoveredItem(item);
        updateTooltipPos(event);
    };

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: `url('${BACKGROUNDS.status}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col px-4 py-6 sm:px-8">
                {error ? <ErrorAlert message={error} /> : null}

                <main className="flex flex-1 items-start justify-start px-4 pt-6 pb-20 sm:px-8 sm:pt-8 sm:pb-24">
                    <div className="flex flex-wrap items-start justify-start gap-8 sm:gap-10">
                        {items.map((item) => (
                            <ItemDisplay
                                key={item.name}
                                item={item}
                                onHoverStart={handleHoverStart}
                                onHoverMove={updateTooltipPos}
                                onHoverEnd={() => setHoveredItem(null)}
                            />
                        ))}
                    </div>
                </main>

                {hoveredItem ? (
                    <Tooltip 
                        title={hoveredItem.name} 
                        lines={`HP : ${getItemHealAmount(hoveredItem.name)}`} 
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
