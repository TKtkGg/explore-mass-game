import Image from "next/image";

type Props = {
    src: string;
    className?: string;
};

export const BattleSpriteEffect = (props: Props) => {
    const { src, className } = props;
    return (
        <div className={`pointer-events-none absolute inset-0 z-30 flex items-center justify-center ${className}`}>
            <Image
                src={src}
                alt=""
                width={280}
                height={280}
                className="animate-battle-slash-pop h-auto w-full object-contain"
                unoptimized
            />
        </div>
    )
};