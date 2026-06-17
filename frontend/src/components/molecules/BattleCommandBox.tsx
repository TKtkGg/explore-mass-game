import { BattleChoice, StatusState } from "@/type/types";
import { HpBar } from "@/components/atoms/HpBar";
import { BattleFloatingNumber } from "../atoms/BattleFloatingNumber";

const BATTLE_CHOICE_CONFIG: Record<
    BattleChoice,
    { label: string; borderClass: string }
> = {
    [BattleChoice.ATTACK]: { label: "攻撃", borderClass: "border-red-500" },
    [BattleChoice.DEFEND]: { label: "守る", borderClass: "border-blue-500" },
    [BattleChoice.ITEM]: { label: "アイテム", borderClass: "border-yellow-400" },
    [BattleChoice.RUN]: { label: "逃げる", borderClass: "border-white" },
};

type Props = {
    player: StatusState | undefined;
    hp: number;
    disabled: boolean;
    onChoice: (choice: BattleChoice) => void;
    itemOptions: string[];
    onItemChoice: (itemName: string) => void;
    onItemBack: () => void;
    ownedItems: Record<string, number>;
    isShaking?: boolean;
    shakeKey?: number;
    damageFloater?: { value: number; key: number } | null;
};

export const BattleCommandBox = (props: Props) => {
    const { player, hp, disabled, onChoice, itemOptions, onItemChoice, onItemBack, ownedItems, isShaking, shakeKey, damageFloater } = props;

    return (
        <div className="relative mt-auto w-full border-t-2 border-yellow-500/80 bg-black/65 px-4 py-4 sm:px-6 sm:py-5">
            {damageFloater ? (
                <div className="pointer-events-none absolute -top-16 left-1/2 z-20 -translate-x-1/2">
                    <div className="relative h-16 w-20">
                        <BattleFloatingNumber key={damageFloater.key} value={damageFloater.value} />
                    </div>
                </div>
            ) : null}
            <div className="mx-auto flex max-w-3xl flex-col gap-4">
                <div key={shakeKey} className={`flex items-center gap-3 sm:gap-4 ${isShaking ? "animate-battle-shake" : ""}`}>
                    <p className="shrink-0 text-sm font-black text-white text-outline sm:text-base">
                        Lv.{player?.level ?? "—"} {player?.name ?? "プレイヤー"}
                    </p>
                    <div className="relative flex-1">
                        <HpBar
                            hp={hp}
                            maxHp={player?.maxHp ?? 1}
                            className="flex-1"
                        />
                    </div>
                    
                </div>

                {itemOptions.length > 0 ? (
                    <div className="flex items-stretch gap-2 sm:gap-3">
                        <button
                            type="button"
                            onClick={onItemBack}
                            disabled={disabled}
                            className="shrink-0 rounded-md border-2 border-white bg-black px-4 py-3 text-sm font-black text-white transition hover:bg-neutral-900 disabled:cursor-not-allowed disabled:opacity-50 sm:px-5 sm:py-4 sm:text-base"
                        >
                            戻る
                        </button>
                        <div className="grid flex-1 grid-cols-1 gap-2 sm:grid-cols-2">
                            {itemOptions.map((item) => (
                                <button
                                    key={item}
                                    type="button"
                                    onClick={() => onItemChoice(item)}
                                    disabled={disabled}
                                    className="rounded-md border-2 border-yellow-400 bg-black px-3 py-2 text-sm font-black text-white transition hover:bg-neutral-900 disabled:cursor-not-allowed disabled:opacity-50 sm:text-base"
                                >
                                    {item} ({ownedItems[item] ?? 0})
                                </button>
                            ))}
                        </div>
                    </div>
                ) : (
                    <div className="grid grid-cols-2 gap-2 sm:grid-cols-4 sm:gap-3">
                        {Object.values(BattleChoice).map((choice) => {
                            const config = BATTLE_CHOICE_CONFIG[choice];
                            return (
                                <button
                                    key={choice}
                                    type="button"
                                    onClick={() => onChoice(choice)}
                                    disabled={disabled}
                                    className={`rounded-md border-2 bg-black px-3 py-3 text-sm font-black text-white transition hover:bg-neutral-900 disabled:cursor-not-allowed disabled:opacity-50 sm:py-4 sm:text-base ${config.borderClass}`}
                                >
                                    {config.label}
                                </button>
                            );
                        })}
                    </div>
                )}
            </div>
        </div>
    );
};
