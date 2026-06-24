import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { getSessionId } from "@/lib/session";

export const useRequireSession = () => {
    const router = useRouter();

    useEffect(() => {
        if (!getSessionId()) {
            router.replace("/");
        }
    }, [router]);
};
