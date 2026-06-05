import { CardState } from "@/type/types";

type Props = {
    card: CardState;
    x: number;
    y: number;
};

export const CardTooltip = (props: Props) => {
    const { card, x, y } = props;

    return (
        <div
            className="pointer-events-none fixed z-50 max-w-xs border-2 bg-black/90 px-4 py-3 shadow-[4px_4px_0_rgba(0,0,0,0.4)] sm:max-w-sm"
            style={{ left: x + 16, top: y + 16 }}
            role="tooltip"
        >
            <p className="text-xl font-black text-white text-outline sm:text-2xl">
                {card.name}
            </p>
            <p className="mt-1 text-sm font-bold leading-relaxed text-white sm:text-base">
                {card.text}
            </p>
        </div>
    );
};
