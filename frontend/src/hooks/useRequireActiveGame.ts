import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { isGameFinished } from "@/lib/session";

export const useRequireActiveGame = () => {
    const router = useRouter();

    useEffect(() => {
        if (isGameFinished()) {
            router.replace("/gameover");
        }
    }, [router]);
};
