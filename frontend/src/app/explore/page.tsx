"use client";
import { useState, useEffect } from "react";
import { apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
export default function ExplorePage() {
    const [remainingSteps, setRemainingSteps] = useState(25);
    const [stopped, setStopped] = useState(false);
    const [error, setError] = useState<Error | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [routeOptions, setRouteOptions] = useState<string[]>([]);
    const router = useRouter();
    
    useEffect(() => {
        const reset = async () => {
            try {
                const response = await apiPost("/reset");
                setRemainingSteps(response.remainingSteps);
                setStopped(response.stopped);
                setRouteOptions(response.routeOptions);
                setError(null);
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
            const response = await apiPost("/move", { routeType: routeType });
            setRemainingSteps(response.remainingSteps);
            setStopped(response.stopped);
            setRouteOptions(response.routeOptions);
            setError(null);
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
            {routeOptions.map((option, index) => (
                <div key={index}>
                    <button onClick={() => handleMove(option)} disabled={isLoading || stopped}>{option}</button>
                    <br />
                </div>
            ))}
            <button onClick={() => router.push("/status")}>STATUS</button>
        </div>
    )
}