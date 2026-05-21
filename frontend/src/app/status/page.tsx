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
            <button onClick={() => router.push("/equipment")}>装備変更</button>
            <p>Own Equipment: {data?.ownedEquipmentList.map((equipment: EquipmentState) => equipment.name).join(", ")}</p>
            <p>Owned Cards: {data?.ownedCards.map((card: CardState) => card.name).join(", ")}</p>
            <p>Owned Items: {Object.entries(data?.ownedItems ?? {}).map(([item, count]) => `${item} (${count})`).join(", ")}</p>
            <button onClick={() => router.push("/explore")}>BACK</button>
        </div>
    )
}