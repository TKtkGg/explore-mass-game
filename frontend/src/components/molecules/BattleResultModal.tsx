import { BattleResultDisplay } from "@/lib/parseBattleResult";

type Props = {
    result: BattleResultDisplay;
    onBack: () => void;
};

export const BattleResultModal = (props: Props) => {
    const { result, onBack } = props;
    const showRewards = result.exp !== null && result.gold !== null;
    const showLevelUp = result.levelFrom !== null && result.levelTo !== null;

    return (
        <div className="absolute inset-0 z-30 flex items-center justify-center px-4">
            <div className="w-full max-w-lg rounded-lg border-2 border-amber-700/90 bg-black/75 px-6 py-8 shadow-[0_8px_32px_rgba(0,0,0,0.5)] sm:px-10 sm:py-10">
                <h2 className="text-center text-4xl font-black text-red-500 text-outline sm:text-5xl md:text-6xl">
                    {result.title}
                </h2>

                {showRewards || showLevelUp ? (
                    <div
                        className={`mt-8 flex gap-6 sm:gap-8 ${
                            showLevelUp
                                ? "flex-col sm:flex-row sm:items-start sm:justify-between"
                                : "flex-col items-center"
                        }`}
                    >
                        {showRewards ? (
                            <div
                                className={`space-y-4 ${showLevelUp ? "sm:flex-1" : "text-center"}`}
                            >
                                <p className="text-lg font-black sm:text-xl">
                                    <span className="text-[rgb(73,254,73)] text-outline">EXP</span>
                                    <span className="text-white"> : {result.exp}</span>
                                </p>
                                <p className="text-lg font-black sm:text-xl">
                                    <span className="text-[rgb(255,215,0)] text-outline">GOLD</span>
                                    <span className="text-white"> : {result.gold}</span>
                                </p>
                            </div>
                        ) : null}

                        {showLevelUp ? (
                            <div className="space-y-2 text-center text-lg font-bold text-white sm:flex-1 sm:text-right sm:text-xl">
                                <p>レベルアップ！</p>
                                <p>
                                    LV : {result.levelFrom} → {result.levelTo}
                                </p>
                            </div>
                        ) : null}
                    </div>
                ) : null}

                <div className="mt-10 flex justify-center">
                    <button
                        type="button"
                        onClick={onBack}
                        className="min-w-[140px] rounded border-2 border-black bg-neutral-300 px-10 py-2.5 text-lg font-bold text-neutral-900 transition hover:bg-neutral-200 active:translate-y-0.5 sm:min-w-[160px] sm:text-xl"
                    >
                        戻る
                    </button>
                </div>
            </div>
        </div>
    );
};
