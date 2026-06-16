type Props = {
    value: number;
    variant?: "damage" | "heal";
}

export const BattleFloatingNumber = (props: Props) => {
    const { value, variant = "damage" } = props;
    const colorClass = variant === "damage" ? "text-red-400" : "text-green-400";

    return (
        <div className="pointer-events-none absolute inset-0 z-10 flex items-center justify-center">
            <span className={`animate-battle-damage-pop text-3xl font-black tabular-nums text-outline sm:text-4xl ${colorClass}`}>
                {value}
            </span>
        </div>
    );
};