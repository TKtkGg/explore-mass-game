import Image from "next/image";
import { ICONS } from "@/lib/imagePaths";
import { CardState } from "@/type/types";

type Props = {
    card: CardState;
    onHoverStart?: (card: CardState, event: React.MouseEvent<HTMLDivElement>) => void;
    onHoverMove?: (event: React.MouseEvent<HTMLDivElement>) => void;
    onHoverEnd?: () => void;
};

export const CardDisplay = (props: Props) => {
    const { card, onHoverStart, onHoverMove, onHoverEnd } = props;

    return (
        <div
            onMouseEnter={(event) => onHoverStart?.(card, event)}
            onMouseMove={(event) => onHoverMove?.(event)}
            onMouseLeave={() => onHoverEnd?.()}
            className="flex flex-col items-center gap-2 sm:gap-3"
        >
            <Image
                src={ICONS.card}
                alt=""
                width={112}
                height={112}
                className="h-auto w-16 object-contain sm:w-32"
                unoptimized
            />
            <span className="text-center text-base font-black text-white text-outline sm:text-3xl">
                {card.name}
            </span>
        </div>
    );
};
