"use client";
import { useState, useEffect } from "react";
import { apiPost, apiGet } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
export default function ExplorePage() {
    const [remainingSteps, setRemainingSteps] = useState(25);
    const [stopped, setStopped] = useState(false);
    const [error, setError] = useState<Error | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [routeOptions, setRouteOptions] = useState<string[]>([]);
    const [message, setMessage] = useState("");
    const router = useRouter();
    
    useEffect(() => {
        const start = async () => {
            try {
                const response = await apiGet("/move/status");
                setRemainingSteps(response.remainingSteps);
                setStopped(response.stopped);
                setRouteOptions(response.routeOptions);
                setMessage(response.message);
                setError(null);
            } catch (error: unknown) {
                if (error instanceof Error) {
                    setError(error);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            }
        };
        start();
    }, []);

    const handleMove = async (routeType: string) => {
        if (isLoading) return;
        setIsLoading(true);

        try {
            let response;
            if (routeType === "REST") {
                response = await apiPost("/move/rest", { routeType: routeType });
            } else if (routeType === "TREASURE") {
                response = await apiPost("/move/treasure", { routeType: routeType });
            } else if (routeType === "CARD") {
                response = await apiPost("/move/card", { routeType: routeType });
                router.push("/card");
            } else {
                response = await apiPost("/move", { routeType: routeType });
            }
            setRemainingSteps(response.remainingSteps);
            setStopped(response.stopped);
            setRouteOptions(response.routeOptions);
            setMessage(response.message);
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

    const handleReset = async () => {
        if (isLoading) return;
        setIsLoading(true);

        try {
            const response = await apiPost("/reset");
            setRemainingSteps(response.remainingSteps);
            setStopped(response.stopped);
            setRouteOptions(response.routeOptions);
            setMessage(response.message);
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
            <p>{message}</p>
            {routeOptions.map((option, index) => (
                <div key={index}>
                    <button onClick={() => handleMove(option)} disabled={isLoading || stopped}>{option}</button>
                    <br />
                </div>
            ))}
            <button onClick={() => router.push("/status")}>STATUS</button>
            <br />
            <button onClick={handleReset} disabled={isLoading}>RESET</button>
        </div>
    )
}