"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { EquipmentState } from "@/type/types";

interface EquipmentResponse {
    ownedEquipmentList: EquipmentState[];
    equipment: EquipmentState;
}

export default function EquipmentPage() {
    const router = useRouter();
    const [data, setData] = useState<EquipmentResponse | null>(null);
    useEffect(() => {
        const fetchEquipment = async () => {
            const response = await apiGet("/equipment");
            setData(response);
        }
        fetchEquipment();
    }, [])

    const changeEquipment = async (name: string) => {
        const response = await apiPost("/equipment/change", { name });
        setData(response);
    }

    return (
        <div>
            <h1>EQUIPMENT</h1>
            <p>Own Equipment: </p>
            {data?.ownedEquipmentList.map((equipment: EquipmentState) => (
                <div key={equipment.name}>
                    <button onClick={() => changeEquipment(equipment.name)}>{equipment.name} (ATK: {equipment.atk}) {(equipment.name === data?.equipment?.name) ? " (装備中)" : ""}</button>
                </div>
            ))}
            <button onClick={() => router.push("/status")}>BACK</button>
        </div>
    )
}