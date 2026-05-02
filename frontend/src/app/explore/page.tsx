"use client";
import { useState, useEffect } from "react";

export default function ExplorePage() {
    const [remainingSteps, setRemainingSteps] = useState();
    const [stopped, setStopped] = useState(false);
    const [error, setError] = useState<Error | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const optionArray: string[] = ["BATTLE", "TREASURE", "REST", "CARD", "SHOP"];

    useEffect(() => {
        const reset = async () => {
            try {
                const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/reset`, {
                    method: "POST",
                });
                if(!response.ok) {
                    throw new Error("Failed to reset");
                } else {
                    const data = await response.json();
                    setRemainingSteps(data.remainingSteps);
                    setStopped(data.stopped);
                    setError(null);
                }
            } catch (error: unknown) {
                if (error instanceof Error) {
                    setError(error);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            }
        };
        reset();
    }, []);

    const handleMove = async (routeType: string) => {
        if (isLoading) return;
        
        setIsLoading(true);

        try {
            const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/move`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ routeType }),
            });
            if(!response.ok) {
                throw new Error("Failed to move");
            } else {
                const data = await response.json();
                setRemainingSteps(data.remainingSteps);
                setStopped(data.stopped);
                setError(null);
            }
        } catch (error: unknown) {
            if (error instanceof Error) {
                setError(error);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsLoading(false);
        }
    }
    return (
        <div>
            <h1>Explore Page</h1>
            <p>Remaining Steps: {remainingSteps}</p>
            <p>Stopped: {stopped ? "Stopping" : "You can go."}</p>
            <p>Error: {error?.message}</p>
            <p>Is Loading: {isLoading ? "Loading..." : ""}</p>
            {optionArray.map((option) => (
                <div key={option}>
                    <button onClick={() => handleMove(option)} disabled={isLoading}>{option}</button>
                    <br />
                </div>
            ))}
        </div>
    )
}