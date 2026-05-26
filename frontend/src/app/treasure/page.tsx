"use client";

import { useState, useEffect } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";

export default function TreasurePage() {
    const router = useRouter();
    const [message, setMessage] = useState<string>("");
    const [isOpen, setIsOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchTreasure = async () => {
            setIsLoading(true);
            try {
                const response = await apiGet("/treasure");
                setMessage(response.message);
            } catch (error: unknown) {
                if(error instanceof Error) {
                    setError(error.message);
                } else {
                    setError("通信に失敗しました。");
                }
            } finally {
                setIsLoading(false);
            }
        };
        fetchTreasure();
    }, []);

    const handleOpenTreasure = async () => {
        setIsOpen(true);
        setIsLoading(true);
        try {
            const response = await apiPost("/treasure/open");
            setMessage(response.message);
        } catch (error: unknown) {
            if(error instanceof Error) {
                setError(error.message);
            } else {
                setError("通信に失敗しました。");
            }
        } finally {
            setIsLoading(false);
        }
    }

    if(isLoading) {
        return <div>Loading...</div>;
    }

    if(error) {
        return <div>{error}</div>;
    }

    return (
        <div>
            <p>{message}</p>
            {isOpen ? (
                <button onClick={() => router.push("/explore")}>BACK</button>
            ) : (
                <button onClick={() => handleOpenTreasure()}>Open Treasure</button>
            )}
        </div>
    )
}