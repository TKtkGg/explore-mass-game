import Image from "next/image";
import { useAudio } from "@/components/providers/AudioProvider";
import { SFX } from "@/lib/audioPaths";

type Props = {
    onClick: () => void;
    img: string;
};

export const IconButton = (props: Props) => {
    const { onClick, img } = props;
    const { playSfx } = useAudio();
    return (
        <button
            type="button"
            onClick={() => {
                playSfx(SFX.button);
                onClick();
            }}
            className="flex size-12 shrink-0 items-center justify-center rounded-xl bg-neutral-300 border-2 border-black transition hover:bg-neutral-200 active:scale-95 sm:size-14"
        >
            <Image
                src={img}
                alt=""
                width={28}
                height={28}
                className="h-7 w-7 object-contain sm:h-8 sm:w-8"
                unoptimized
            />
        </button>
    );
};
