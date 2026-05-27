"use client";

import { useEffect, useState } from "react";
import Image from "next/image";
import { apiPost, apiGet } from "@/lib/apiClient";
import { useRouter } from "next/navigation";
import { CardState } from "@/type/types";
import { Title } from "@/components/atoms/Title";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";

export default function CardPage() {
    const router = useRouter();
    const [cards, setCards] = useState<CardState[] | null>(null);
    const [chosenCard, setChosenCard] = useState<CardState | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [isChoosing, setIsChoosing] = useState(false);

    useEffect(() => {
        const fetchCards = async () => {
            setIsLoading(true);
            try {
                const response = await apiGet("/card");
                setCards(response.display ?? []);
                setChosenCard(null);
                setError(null);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err);
                } else {
                    setError(new Error("通信に失敗しました。"));
                }
            } finally {
                setIsLoading(false);
            }
        };
        fetchCards();
    }, []);

    const handleChooseCard = async (card: CardState) => {
        if (chosenCard || isChoosing || isLoading) return;

        setIsChoosing(true);
        try {
            const response = await apiPost("/card/choose", { chosenCard: card });
            setCards(response.display ?? []);
            setChosenCard(response.chosenCard ?? card);
            setError(null);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        } finally {
            setIsChoosing(false);
        }
    };

    const titleText = (() => {
        if (isLoading) return "読み込み中…";
        if (chosenCard) return `"${chosenCard.name}"を手に入れた`;
        return "カードを選べ";
    })();

    const showBackFooter = chosenCard !== null || error !== null;

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: "url('/background/洞窟.jpg')" }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col">
                <header className="px-4 pt-8 text-center sm:pt-10">
                    <Title>{titleText}</Title>
                </header>

                {error ? <ErrorAlert message={error.message} /> : null}

                <div className="flex flex-1 flex-wrap items-center justify-center px-4 py-8">
                    {cards?.map((card, index) => {
                        const isThisChosen =
                            chosenCard !== null && chosenCard.name === card.name;
                        const isDisabled =
                            isChoosing ||
                            isLoading ||
                            (chosenCard !== null && !isThisChosen);

                        return (
                            <button
                                key={`${card.name}-${index}`}
                                type="button"
                                onClick={() => handleChooseCard(card)}
                                disabled={isDisabled}
                                aria-label={`カード ${index + 1}`}
                                className="relative shrink-0 outline-none transition cursor-pointer focus-visible:ring-2 focus-visible:ring-sky-400 focus-visible:ring-offset-2 focus-visible:ring-offset-neutral-900 disabled:cursor-not-allowed disabled:opacity-40 disabled:grayscale"
                            >
                                {isThisChosen ? (
                                    <span className="relative block w-[min(26vw,140px)] sm:w-[min(22vw,280px)] md:w-[200px]">
                                        <Image
                                            src="/img/表向きのカード.png"
                                            alt=""
                                            width={200}
                                            height={280}
                                            className="h-auto w-full object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.45)]"
                                            unoptimized
                                            priority
                                        />
                                        <div className="absolute inset-[9%_10%_11%_10%] flex flex-col overflow-hidden text-left text-neutral-900">
                                            <p className="line-clamp-2 text-xs font-black leading-tight sm:text-sm">
                                                {card.name}
                                            </p>
                                            <div className="min-h-0 flex-1 overflow-y-auto text-[10px] leading-snug sm:text-xs">
                                                <p className="whitespace-pre-wrap break-words pr-0.5">
                                                    {card.text}
                                                </p>
                                            </div>
                                        </div>
                                    </span>
                                ) : (
                                    <span className="block transition enabled:active:translate-y-0.5 enabled:active:scale-[0.98]">
                                        <Image
                                            src="/img/裏向きのカード.png"
                                            alt=""
                                            width={200}
                                            height={280}
                                            className="h-auto w-[300px] object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.45)] sm:w-[350px] md:w-[400px]"
                                            unoptimized
                                            priority={index === 0}
                                        />
                                    </span>
                                )}
                            </button>
                        );
                    })}
                </div>

                {isChoosing ? (
                    <p className="pb-2 text-center text-xs font-bold text-white text-outline">
                        送信中…
                    </p>
                ) : null}

                {showBackFooter ? (
                    <footer className="absolute bottom-6 left-4 z-20 sm:bottom-8 sm:left-6">
                        <MainButton onClick={() => router.push("/explore")}>戻る</MainButton>
                    </footer>
                ) : null}
            </div>
        </div>
    );
}
