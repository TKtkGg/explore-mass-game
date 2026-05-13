"use client";

import { useEffect } from "react";
import { apiPost, apiGet } from "@/lib/apiClient";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { CardState } from "@/type/types";

export default function CardPage() {
    const router = useRouter();
    const [cards, setCards] = useState<CardState[] | null>(null);
    const [chosenCard, setChosenCard] = useState<CardState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    useEffect(() => {
        const fetchCards = async () => {
            const response = await apiGet("/card");
            setCards(response.display);
            setChosenCard(null);
        }
        fetchCards();
    }, []);

    const handleChooseCard = async (card: CardState) => {
        try {
            const response = await apiPost("/card/choose", { chosenCard: card });
            setCards(null);
            setChosenCard(response.chosenCard);
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
            <h1>カード</h1>
            {cards?.map((card: CardState) => (
                <div key={card.name}>
                    <button onClick={() => handleChooseCard(card)}>???</button>
                    <br />
                </div>
            ))}
            {chosenCard && !error && (
                <div>
                    <h2>選んだカード</h2>
                    <h3>{chosenCard.name}</h3>
                    <p>{chosenCard.text}</p>
                    <button onClick={() => router.push("/explore")}>BACK</button>
                </div>
            )}
            {error && (
                <div>
                    <p>{error.message}</p>
                    <button onClick={() => router.push("/explore")}>BACK</button>
                </div>
            )}
        </div>
    );
}