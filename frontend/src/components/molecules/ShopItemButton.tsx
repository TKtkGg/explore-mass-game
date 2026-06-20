import Image from "next/image";
import { MerchandiseState } from "@/type/types";
import { useAudio } from "@/components/providers/AudioProvider";
import { SFX } from "@/lib/audioPaths";

type Props = {
    merchandise: MerchandiseState;
    onClick: (merchandise: MerchandiseState) => void;
    disabled?: boolean;
};

function getMerchandiseIcon(type: string): string {
    switch (type) {
        case "EQUIPMENT":
            return "/icon/武器.png";
        case "ITEM":
            return "/icon/回復.png";
        case "CARD":
            return "/icon/カード.png";
        default:
            return "/icon/武器.png";
    }
}

export const ShopItemButton = (props: Props) => {
    const { merchandise, onClick, disabled = false } = props;
    const iconSrc = getMerchandiseIcon(merchandise.type);
    const { playSfx } = useAudio();
    
    return (
        <button
            type="button"
            onClick={() => {
                playSfx(SFX.buy);
                onClick(merchandise);
            }}
            disabled={disabled}
            aria-label={`${merchandise.name} ${merchandise.price}G`}
            className="flex min-w-0 flex-1 flex-col items-center gap-2 outline-none transition cursor-pointer focus-visible:ring-2 focus-visible:ring-sky-400 focus-visible:ring-offset-2 focus-visible:ring-offset-neutral-900 disabled:cursor-not-allowed disabled:opacity-40"
        >
            <p className="line-clamp-2 text-center text-sm font-black text-white text-outline sm:text-base md:text-lg">
                {merchandise.name}
            </p>
            <Image
                src={iconSrc}
                alt=""
                width={120}
                height={120}
                className="h-auto w-[min(18vw,120px)] object-contain drop-shadow-[0_4px_12px_rgba(0,0,0,0.5)] sm:w-[min(16vw,140px)] md:w-[150px]"
                unoptimized
            />
            <p className="text-sm font-black text-white text-outline sm:text-base md:text-lg">
                {merchandise.price}G
            </p>
        </button>
    );
};
