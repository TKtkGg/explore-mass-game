"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { CardState, EquipmentState, ShopState } from "@/type/types";

export default function ShopPage() {
    const router = useRouter();
    const [shop, setShop] = useState<ShopState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    useEffect(() => {
        const fetchShop = async () => {
            const response = await apiGet("/shop");
            setShop(response);
        }
        fetchShop();
    }, []);

    const handleBuyCard = async (card: CardState) => {
        try {
            const response = await apiPost("/shop/buyCard", { chosenCard: card });
            setShop(response);
        } catch (error: unknown) {
            if (error instanceof Error) {
                setError(error);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        }
    }

    const handleBuyEquipment = async (equipment: EquipmentState) => {
        try {
            const response = await apiPost("/shop/buyEquipment", { chosenEquipment: equipment });
            setShop(response);
        } catch (error: unknown) {
            if (error instanceof Error) {
                setError(error);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        }
    }

    return (
        <div>
            <h1>ショップ</h1>
            <div>
                <h2>所持金: {shop?.gold}</h2>
                <div>
                    <h3>カード</h3>
                    <ul>
                        {shop?.unownedCards.map((card: CardState) => (
                            <button key={card.name} onClick={() => handleBuyCard(card)}>{card.name}({card.price}G)</button>
                        ))}
                    </ul>
                </div>
                <div>
                    <h3>装備</h3>
                    <ul>
                        {shop?.unownedEquipments.map((equipment: EquipmentState) => (
                            <button key={equipment.name} onClick={() => handleBuyEquipment(equipment)}>{equipment.name}({equipment.price}G)</button>
                        ))}
                    </ul>
                </div>
            </div>
            <div>
                <h2>{shop?.message}</h2>
            </div>
            <button onClick={() => router.push("/explore")}>BACK</button>
            {error && <p>{error.message}</p>}
        </div>
    );
}