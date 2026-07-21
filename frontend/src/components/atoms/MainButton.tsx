import { useAudio } from "@/components/providers/AudioProvider";
import { SFX } from "@/lib/audioPaths";

type Props = {
    children: React.ReactNode;
    onClick: () => void;
    kind?: "button" | "back" | "start";
    className?: string;
    disabled?: boolean;
}

export const MainButton = (props: Props) => {
    const { children, onClick, kind = "button", className, disabled = false } = props;
    const { playSfx } = useAudio();
    return (
        <button
            type="button"
            disabled={disabled}
            onClick={() => {
                if (disabled) return;
                playSfx(SFX[kind]);
                onClick();
            }}
            className={`min-w-[200px] rounded-md border-2 border-black px-8 py-3 text-lg font-black tracking-widest shadow-[4px_4px_0_#000] transition sm:min-w-[240px] sm:text-xl ${
                disabled
                    ? "cursor-not-allowed border-neutral-500 bg-neutral-400 text-neutral-600 shadow-none"
                    : "cursor-pointer bg-white text-neutral-900 hover:bg-neutral-100 active:translate-y-0.5 active:shadow-none"
            } ${className ?? ""}`}
        >
            {children}
        </button>
    )
}
