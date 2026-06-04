import Image from "next/image";
import { EquipmentState } from "@/type/types";

type Props = {
    equipment: EquipmentState;
    isEquipped: boolean;
    onClick: (name: string) => void;
    onHoverStart?: (
        equipment: EquipmentState,
        event: React.MouseEvent<HTMLButtonElement>
    ) => void;
    onHoverMove?: (event: React.MouseEvent<HTMLButtonElement>) => void;
    onHoverEnd?: () => void;
    disabled?: boolean;
};

export const EquipmentButton = (props: Props) => {
    const {
        equipment,
        isEquipped,
        onClick,
        onHoverStart,
        onHoverMove,
        onHoverEnd,
        disabled = false,
    } = props;

    return (
        <button
            type="button"
            onClick={() => onClick(equipment.name)}
            disabled={disabled}
            aria-label={equipment.name}
            onMouseEnter={(event) => onHoverStart?.(equipment, event)}
            onMouseMove={(event) => onHoverMove?.(event)}
            onMouseLeave={() => onHoverEnd?.()}
            className="flex flex-col items-center gap-3 outline-none transition disabled:cursor-not-allowed disabled:opacity-50"
        >
            <Image
                src="/icon/武器.png"
                alt=""
                width={112}
                height={112}
                className="h-auto w-24 object-contain transition enabled:active:scale-95 sm:w-32"
                unoptimized
            />
            <span
                className={`text-center text-2xl font-black text-outline sm:text-3xl ${
                    isEquipped ? "text-yellow-300" : "text-white"
                }`}
            >
                {equipment.name}
            </span>
        </button>
    );
};
