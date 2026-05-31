import { BattleChoice, StatusState } from "@/type/types";
import { HpBar } from "@/components/atoms/HpBar";

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
    disabled: boolean;
    onChoice: (choice: BattleChoice) => void;
    itemOptions: string[];
    onItemChoice: (itemName: string) => void;
    ownedItems: Record<string, number>;
};

export const BattleCommandBox = (props: Props) => {
    const { player, disabled, onChoice, itemOptions, onItemChoice, ownedItems } = props;

    return (
        <div className="mt-auto w-full border-t-2 border-yellow-500/80 bg-black/65 px-4 py-4 sm:px-6 sm:py-5">
            <div className="mx-auto flex max-w-3xl flex-col gap-4">
                <div className="flex items-center gap-3 sm:gap-4">
                    <p className="shrink-0 text-sm font-black text-white text-outline sm:text-base">
                        Lv.{player?.level ?? "—"} {player?.name ?? "プレイヤー"}
                    </p>
                    <HpBar
                        hp={player?.hp ?? 0}
                        maxHp={player?.maxHp ?? 1}
                        className="flex-1"
                    />
                </div>

                {itemOptions.length > 0 ? (
                    <div className="grid grid-cols-2 gap-2 sm:grid-cols-3">
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
