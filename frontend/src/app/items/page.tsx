"use client";

import { useEffect, useState } from "react";
import { apiGet } from "@/lib/apiClient";
import { useRouter } from "next/navigation";

interface ItemResponse {
    ownedItems: {
        [key: string]: number;
    };
}

export default function ItemsPage() {
    const router = useRouter();
    const [data, setData] = useState<ItemResponse | null>(null);
    useEffect(() => {
        const fetchItems = async () => {
            const response = await apiGet("/items");
            setData(response);
        }
        fetchItems();
    }, []);

    return (
        <div>
            <p>Owned Items: {Object.entries(data?.ownedItems ?? {}).map(([item, count]) => `${item} (${count})`).join(", ")}</p>
            <button onClick={() => router.push("/status")}>BACK</button>
        </div>
    )
}