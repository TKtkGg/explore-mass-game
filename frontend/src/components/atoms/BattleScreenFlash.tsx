type Props = {
    type: "hit" | "defend" | "heal";
}

const FLASH_CLASS = {
    hit: "animate-battle-flash-hit bg-white",
    defend: "animate-battle-flash-defend bg-blue-400",
    heal: "animate-battle-flash-heal bg-green-400",
} as const;

export const BattleScreenFlash = ({ type }: Props) => {
    return (
        <div className={`pointer-events-none absolute inset-0 z-20 rounded-lg ${FLASH_CLASS[type]}`} />
    )
};