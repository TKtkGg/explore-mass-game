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

interface StatusResponse {
    name: string;
    level: number;
    maxHp: number;
    hp: number;
    atk: number;
    def: number;
    spd: number;
    exp: number;
    gold: number;
    equipment: EquipmentState;
    ownEquipmentList: EquipmentState[];
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
            <p>ATK: {data?.atk}</p>
            <p>DEF: {data?.def}</p>
            <p>SPD: {data?.spd}</p>
            <p>EXP: {data?.exp}</p>
            <p>Gold: {data?.gold}</p>
            <p>Equipment: {data?.equipment.name} (ATK: {data?.equipment.atk})</p>
            <p>Own Equipment: {data?.ownEquipmentList.map((equipment) => equipment.name).join(", ")}</p>
            <button onClick={() => router.push("/explore")}>BACK</button>
        </div>
    )
}