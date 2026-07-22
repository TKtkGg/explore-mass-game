"use client";

import { useRouter } from "next/navigation";
import { useAudio } from "@/components/providers/AudioProvider";
import { MainButton } from "@/components/atoms/MainButton";
import { clearSessionId } from "@/lib/session";
import { apiPost, apiGet } from "@/lib/apiClient";
import { useEffect, useState } from "react";

type Props = {
    onClose: () => void;
};

type VolumeRowProps = {
    label: string;
    volume: number;
    onChange: (value: number) => void;
};

const VolumeRow = ({ label, volume, onChange }: VolumeRowProps) => {
    const percent = Math.round(volume * 100);

    return (
        <div className="flex items-center gap-4 sm:gap-6">
            <span className="w-16 shrink-0 text-right text-2xl font-black text-white text-outline sm:text-3xl">
                {label}
            </span>
            <input
                type="range"
                min={0}
                max={100}
                value={percent}
                onChange={(e) => onChange(Number(e.target.value) / 100)}
                aria-label={`${label}音量`}
                className="h-2 flex-1 cursor-pointer appearance-none rounded-full bg-white/30 accent-white"
            />
            <span className="w-16 shrink-0 text-right text-2xl font-black tabular-nums text-white text-outline sm:text-3xl">
                {percent}%
            </span>
        </div>
    );
};

export const SettingModal = (props: Props) => {
    const { onClose } = props;
    const router = useRouter();
    const { bgmVolume, sfxVolume, setBgmVolume, setSfxVolume, stopBgm } = useAudio();
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const fetchUser = async () => {
            const user = await apiGet("/auth/user");
            if (user.authenticated) {
                setIsLoggedIn(true);
            } else {
                setIsLoggedIn(false);
            }
        }
        fetchUser();
    }, []);

    const handleSave = async () => {
        try {
            if (isLoggedIn) {
                await apiPost("/save");
            }
            router.push("/");
            clearSessionId();
            stopBgm();
        } catch {
            alert("保存に失敗しました。");
        }
    }

    return (
        <div
            className="absolute inset-0 z-40 flex items-center justify-center bg-black/70 px-4"
            onClick={onClose}
        >
            <div
                className="w-full max-w-2xl rounded-3xl border-4 border-sky-400 bg-black/85 px-6 py-8 shadow-[0_8px_32px_rgba(0,0,0,0.6)] sm:px-12 sm:py-10"
                onClick={(e) => e.stopPropagation()}
            >
                <h2 className="text-center text-4xl font-black text-white text-outline sm:text-5xl">
                    Setting
                </h2>

                <div className="mt-8 flex flex-col gap-6 sm:mt-10 sm:gap-8">
                    <VolumeRow label="BGM" volume={bgmVolume} onChange={setBgmVolume} />
                    <VolumeRow label="SE" volume={sfxVolume} onChange={setSfxVolume} />
                </div>

                <div className="mt-10 flex justify-between gap-2 sm:mt-12 sm:gap-4">
                    <MainButton
                        onClick={onClose}
                        kind="back"
                        className="!min-w-0 flex-1 px-3 py-2.5 text-sm tracking-wide sm:!min-w-[240px] sm:flex-none sm:px-8 sm:py-3 sm:text-xl sm:tracking-widest"
                    >
                        戻る
                    </MainButton>
                    <MainButton
                        onClick={handleSave}
                        kind="back"
                        className="!min-w-0 flex-1 px-3 py-2.5 text-sm tracking-wide sm:!min-w-[240px] sm:flex-none sm:px-8 sm:py-3 sm:text-xl sm:tracking-widest"
                    >
                        {isLoggedIn ? "セーブしてタイトルへ" : "タイトル"}
                    </MainButton>
                </div>
            </div>
        </div>
    );
};
