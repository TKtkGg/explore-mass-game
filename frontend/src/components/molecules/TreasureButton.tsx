import Image from "next/image";
import { useAudio } from "@/components/providers/AudioProvider";
import { SFX } from "@/lib/audioPaths";

type Props = {
    isOpen: boolean;
    isOpening: boolean;
    isLoading: boolean;
    handleOpenTreasure: () => void;
}

export const TreasureButton = (props: Props) => {
    const { isOpen, isOpening, isLoading, handleOpenTreasure } = props;
    const { playSfx } = useAudio();
    return (
        <>
            <button
                type="button"
                onClick={() => {
                    playSfx(SFX.treasure);
                    handleOpenTreasure();
                }}
                disabled={isOpen || isOpening || isLoading}
                className="group outline-none transition cursor-pointer disabled:cursor-default"
                aria-label={isOpen ? "開いた宝箱" : "宝箱を開ける"}
            >
                <span className="block transition group-enabled:group-active:translate-y-1 group-enabled:group-active:scale-[0.98] group-disabled:opacity-90">
                    <Image
                        src={isOpen ? "/img/開いた宝箱.png" : "/img/閉じた宝箱.png"}
                        alt=""
                        width={400}
                        height={320}
                        className="h-auto w-[min(72vw,360px)] max-w-full object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.55)] sm:w-[min(56vw,420px)]"
                        unoptimized
                        priority
                    />
                </span>
            </button>
            {isOpening ? (
                <p className="pb-2 text-center text-xs font-bold text-white text-outline">
                    開封中…
                </p>
            ) : null}
        </>
    )
}