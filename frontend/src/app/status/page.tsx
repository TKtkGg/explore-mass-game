"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";

import { CardState, EquipmentState, StatusState } from "@/type/types";

export default function StatusPage() {
    const router = useRouter();
    const [data, setData] = useState<StatusState | null>(null);

    useEffect(() => {
        const fetchStatus = async () => {
            const response = await apiGet("/status");
            setData(response);
        }
        fetchStatus();
    }, []);
    
    return (
        <div>
            <h1>STATUS</h1>
            <p>Name: {data?.name}</p>
            <p>Level: {data?.level}</p>
            <p>HP: {data?.hp}/{data?.maxHp}</p>
            <p>ATK: {data?.totalAtk}({data?.atk}+{data?.equipment.atk}{data?.ownedCards.map((card: CardState) => card.name === "装備マスター" ? "×1.5" : "")})</p>
            <p>DEF: {data?.def}</p>
            <p>SPD: {data?.spd}</p>
            <p>EXP: {data?.exp}/{data?.nextLevelExp}</p>
            <p>Gold: {data?.gold}</p>
            <p>Equipment: {data?.equipment.name} (ATK: {data?.equipment.atk})</p>
            <button onClick={() => router.push("/equipment")}>装備一覧</button>
            <p>Own Equipment: {(() => {
                const equipmentNames = [];
                for(const equipment of data?.ownedEquipmentList ?? []) {
                    if(equipmentNames.length >= 3) {
                        equipmentNames.push("...");
                        break;
                    }
                    equipmentNames.push(equipment.name);
                }
                return equipmentNames.join(", ");
            })()}</p>
            <button onClick={() => router.push("/cards")}>カード一覧</button>
            <p>Owned Cards: {(() => {
                const cardNames = [];
                for(const card of data?.ownedCards ?? []) {
                    if(cardNames.length >= 3) {
                        cardNames.push("...");
                        break;
                    }
                    cardNames.push(card.name);
                }
                return cardNames.join(", ");
            })()}</p>
            <p>Owned Items: {Object.entries(data?.ownedItems ?? {}).map(([item, count]) => `${item} (${count})`).join(", ")}</p>
            <button onClick={() => router.push("/explore")}>BACK</button>
        </div>
    )
}