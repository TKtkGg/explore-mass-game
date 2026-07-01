import Image from "next/image";
import { ICONS } from "@/lib/imagePaths";

export type OwnedItemDisplay = {
    name: string;
    count: number;
};

type Props = {
    item: OwnedItemDisplay;
    onHoverStart?: (item: OwnedItemDisplay, event: React.MouseEvent<HTMLDivElement>) => void;
    onHoverMove?: (event: React.MouseEvent<HTMLDivElement>) => void;
    onHoverEnd?: () => void;
};

export const ItemDisplay = (props: Props) => {
    const { item, onHoverStart, onHoverMove, onHoverEnd } = props;

    return (
        <div
            onMouseEnter={(event) => onHoverStart?.(item, event)}
            onMouseMove={(event) => onHoverMove?.(event)}
            onMouseLeave={() => onHoverEnd?.()}
            className="flex flex-col items-center gap-2 sm:gap-3"
        >
            <Image
                src={ICONS.heal}
                alt=""
                width={112}
                height={112}
                className="h-auto w-16 object-contain sm:w-32"
                unoptimized
            />
            <div className="text-center text-base font-black text-white text-outline sm:text-3xl">
                <p>{item.name}</p>
                <p>({item.count})</p>
            </div>
        </div>
    );
};
