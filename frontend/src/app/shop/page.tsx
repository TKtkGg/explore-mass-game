"use client";

import { useEffect, useState } from "react";
import { apiGet, apiPost } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { MerchandiseState, ShopState } from "@/type/types";

export default function ShopPage() {
    const router = useRouter();
    const [shop, setShop] = useState<ShopState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    useEffect(() => {
        const fetchShop = async () => {
            const response = await apiGet("/shop");
            setShop(response);
        }
        fetchShop();
    }, []);

    const handleBuyMerchandise = async (merchandise: MerchandiseState) => {
        try {
            const response = await apiPost("/shop/buy", { name: merchandise.name });
            setShop(response);
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
            <h1>ショップ</h1>
            <div>
                <h2>所持金: {shop?.gold}</h2>
                <div>
                    <h3>カード</h3>
                    <ul>
                        {shop?.display.map((merchandise: MerchandiseState) => (
                            <button key={merchandise.name} onClick={() => handleBuyMerchandise(merchandise)}>{merchandise.name}({merchandise.price}G)</button>
                        ))}
                    </ul>
                </div>
            </div>
            <div>
                <h2>{shop?.message}</h2>
            </div>
            <button onClick={() => router.push("/explore")}>BACK</button>
            {error && <p>{error.message}</p>}
        </div>
    );
}