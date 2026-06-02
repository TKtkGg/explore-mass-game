"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";
import { CardState } from "@/type/types";

interface CardsResponse {
    ownedCards: CardState[];
}

export default function CardsPage() {
    const router = useRouter();
    const [data, setData] = useState<CardsResponse | null>(null);
    useEffect(() => {
        const fetchCards = async () => {
            const response = await apiGet("/cards");
            setData(response);
        }
        fetchCards();
    }, []);
    return (
        <div>
            <h1>CARDS</h1>
            <p>Owned Cards: {data?.ownedCards.map((card: CardState) => card.name).join(", ")}</p>
            <button onClick={() => router.push("/status")}>BACK</button>
        </div>
    )
}