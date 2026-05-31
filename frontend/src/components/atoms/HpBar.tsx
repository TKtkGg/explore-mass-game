import { getHpColor } from "@/lib/getHpColor";

type Props = {
    hp: number;
    maxHp: number;
    className?: string;
};

export const HpBar = (props: Props) => {
    const { hp, maxHp, className = "" } = props;
    const ratio = maxHp > 0 ? Math.max(0, Math.min(100, (hp / maxHp) * 100)) : 0;

    return (
        <div
            className={`relative h-7 w-full overflow-hidden rounded-full border-2 border-black bg-neutral-800 sm:h-8 ${className}`}
            role="progressbar"
            aria-valuenow={hp}
            aria-valuemin={0}
            aria-valuemax={maxHp}
        >
            <div
                className="absolute inset-y-0 left-0 transition-[width] duration-300"
                style={{ width: `${ratio}%`, backgroundColor: getHpColor(ratio) }}
            />
            <span className="relative z-10 flex h-full items-center justify-center text-sm font-black tabular-nums text-white text-outline sm:text-base">
                {hp}
            </span>
        </div>
    );
};
