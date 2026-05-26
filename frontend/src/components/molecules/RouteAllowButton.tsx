import Image from "next/image";

type Props = {
    option: string;
    slot: {
        positionClass: string;
        rotationClass: string;
    };
    handleMove: (option: string) => void;
    disabled: boolean;
}

export const RouteAllowButton = (props: Props) => {
    const { option, slot, handleMove, disabled } = props;
    return (
        <div
            className={`absolute flex flex-col items-center gap-2 sm:gap-3 ${slot.positionClass}`}
        >
            <button
            type="button"
            onClick={() => handleMove(option)}
            disabled={disabled}
            className="group flex flex-col items-center outline-none transition cursor-pointer disabled:opacity-40 disabled:grayscale"
            aria-label={`${option}へ進む`}
            >
            <span className="transition group-active:translate-y-0.5 group-active:shadow-none sm:p-2">
                <Image
                src="/img/進行ボタン.png"
                alt=""
                width={150}
                height={150}
                className={`h-16 w-16 object-contain drop-shadow sm:h-44 sm:w-44 ${slot.rotationClass}`}
                unoptimized
                />
            </span>
            <span
                className={`max-w-[28vw] truncate text-center text-sm font-black uppercase text-white sm:max-w-[140px] sm:text-lg text-outline`}
            >
                {option}
            </span>
            </button>
        </div>
    )
}