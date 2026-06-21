"use client";

import { createContext, useContext, useRef, useCallback, useState } from "react";

type AudioContextValue = {
    unlockAudio: () => void;
    playBgm: (src: string) => void;
    stopBgm: () => void;
    playSfx: (src: string) => void;
    setBgmVolume: (v: number) => void;
    setSfxVolume: (v: number) => void;
}

const AudioContext = createContext<AudioContextValue | null>(null);

export function AudioProvider({ children }: { children: React.ReactNode }) {
    const bgmRef = useRef<HTMLAudioElement | null>(null);
    const [unlocked, setUnlocked] = useState(false);
    const bgmVolume = useRef(0.3);
    const sfxVolume = useRef(0.8);

    const unlockAudio = useCallback(() => {
        setUnlocked(true);
        bgmRef.current?.play().catch(() => {});
    }, []);

    const playBgm = useCallback((src: string) => {
        const audio = bgmRef.current;
        if (!audio) return;
        if (audio.src.endsWith(src) && !audio.paused) return;
        audio.src = src;
        audio.loop = true;
        audio.volume = bgmVolume.current;
        if (unlocked) audio.play().catch(() => {});
    }, [unlocked]);

    const stopBgm = useCallback(() => {
        bgmRef.current?.pause();
    }, []);

    const playSfx = useCallback((src: string) => {
        if (!unlocked) return;
        const audio = new Audio(src);
        audio.volume = sfxVolume.current;
        audio.play().catch(() => {});
    }, [unlocked]);

    const setBgmVolume = useCallback((v: number) => {
        bgmVolume.current = Math.max(0, Math.min(1, v));
        if (bgmRef.current) {
            bgmRef.current.volume = bgmVolume.current;
        }
    }, []);

    const setSfxVolume = useCallback((v: number) => {
        sfxVolume.current = Math.max(0, Math.min(1, v));
    }, []);

    return (
        <AudioContext.Provider value={{ unlockAudio, playBgm, stopBgm, playSfx, setBgmVolume, setSfxVolume }}>
            {children}
            <audio ref={bgmRef} preload="auto" />
        </AudioContext.Provider>
    )
    
}

export function useAudio() {
    const ctx = useContext(AudioContext);
    if (!ctx) throw new Error("useAudio must be used within AudioProvider");
    return ctx;
}