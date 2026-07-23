"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiPost } from "@/lib/apiClient";
import { Title } from "@/components/atoms/Title";
import { Input } from "@/components/atoms/Input";
import { MainButton } from "@/components/atoms/MainButton";
import { ErrorAlert } from "@/components/atoms/ErrorAlert";
import { BACKGROUNDS } from "@/lib/imagePaths";

export default function SignupPage() {
    const router = useRouter();
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null);

    const handleRegister = async () => {
        try {
            await apiPost("/auth/register", { username, email, password });
            setError(null);
            router.push("/login");
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("通信に失敗しました。");
            }
        }
    };

    return (
        <div className="relative min-h-[100dvh] w-full overflow-hidden bg-neutral-900">
            <div
                className="pointer-events-none absolute inset-0 bg-cover bg-center bg-no-repeat"
                style={{ backgroundImage: `url('${BACKGROUNDS.woodPlank}')` }}
                aria-hidden
            />

            <div className="relative z-10 flex min-h-[100dvh] flex-col items-center px-4 py-8 sm:px-8">
                <header className="pt-[4vh] text-center sm:pt-[6vh]">
                    <Title>SIGNUP</Title>
                </header>

                <main className="flex w-full flex-1 items-center justify-center py-6">
                    <div className="flex w-full max-w-[512px] flex-col items-center justify-between gap-10 border-[5px] border-[rgba(139,105,20,0.9)] bg-black/75 px-10 py-12 sm:min-h-[533px] sm:px-12 sm:py-14">
                        <div className="flex w-full flex-col items-center gap-8 sm:gap-10">
                            <Input
                                placeholder="username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                className="max-w-[351px] rounded-[15px] border-[3px] border-white py-3 sm:py-3"
                            />
                            <Input
                                type="email"
                                placeholder="E-mail"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className="max-w-[351px] rounded-[15px] border-[3px] border-white py-3 sm:py-3"
                            />
                            <Input
                                type="password"
                                placeholder="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="max-w-[351px] rounded-[15px] border-[3px] border-white py-3 sm:py-3"
                            />
                        </div>

                        <MainButton
                            onClick={handleRegister}
                            className="!min-w-[265px] !rounded-none !px-6 !py-5 !text-[40px] !leading-none !tracking-[1.2px]"
                        >
                            REGISTER
                        </MainButton>

                        {error ? <ErrorAlert message={error} /> : null}
                    </div>
                </main>

                <footer className="flex justify-center pb-2 sm:justify-start sm:pb-4">
                    <MainButton onClick={() => router.push("/")} kind="back">
                        戻る
                    </MainButton>
                </footer>
            </div>
        </div>
    );
}
