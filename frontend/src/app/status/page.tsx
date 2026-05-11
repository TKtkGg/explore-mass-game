"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";

interface EquipmentState {
    name: string;
    atk: number;
    price: number;
    chance: number;
}

interface CardState {
    name: string;
    text: string;
    price: number;
}

interface StatusResponse {
    name: string;
    level: number;
    maxHp: number;
    hp: number;
    atk: number;
    totalAtk: number;
    def: number;
    spd: number;
    exp: number;
    gold: number;
    equipment: EquipmentState;
    ownEquipmentList: EquipmentState[];
    ownedCards: CardState[];
}

export default function StatusPage() {
    const router = useRouter();
    const [data, setData] = useState<StatusResponse | null>(null);

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
            <p>ATK: {data?.totalAtk}({data?.atk}+{data?.equipment.atk})</p>
            <p>DEF: {data?.def}</p>
            <p>SPD: {data?.spd}</p>
            <p>EXP: {data?.exp}</p>
            <p>Gold: {data?.gold}</p>
            <p>Equipment: {data?.equipment.name} (ATK: {data?.equipment.atk})</p>
            <button onClick={() => router.push("/equipment")}>装備変更</button>
            <p>Own Equipment: {data?.ownEquipmentList.map((equipment) => equipment.name).join(", ")}</p>
            <p>Owned Cards: {data?.ownedCards.map((card) => card.name).join(", ")}</p>
            <button onClick={() => router.push("/explore")}>BACK</button>
        </div>
    )
}