"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";

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
            <button onClick={() => router.push("/explore")}>BACK</button>
        </div>
    )
}