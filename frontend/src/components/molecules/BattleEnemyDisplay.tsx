import Image from "next/image";
import { EnemyState } from "@/type/types";
import { HpBar } from "@/components/atoms/HpBar";
import { BattleFloatingNumber } from "../atoms/BattleFloatingNumber";

type Props = {
    enemy: EnemyState | undefined;
    hp: number;
    isShaking?: boolean;
    shakeKey?: number;
    damageFloater?: { value: number; key: number } | null;
};

export const BattleEnemyDisplay = (props: Props) => {
    const { enemy, hp, isShaking, shakeKey, damageFloater } = props;

    if (!enemy) return null;

    return (
        <div key={shakeKey} className={isShaking ? "animate-battle-shake" : ""}>
            <div className="flex w-full max-w-md flex-col items-center gap-3 px-4 sm:max-w-lg sm:gap-4">
                <p className="text-lg font-black text-white text-outline sm:text-xl md:text-2xl">
                    Lv.{enemy.level} {enemy.name}
                </p>
                <HpBar hp={hp} maxHp={enemy.maxHp} className="max-w-xs sm:max-w-sm" />
                <div className="relative">
                    {enemy.imagePath ? (
                        <Image
                            src={enemy.imagePath}
                            alt={enemy.name}
                            width={240}
                            height={240}
                            className="h-auto w-[min(55vw,240px)] object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.35)] sm:w-[min(45vw,280px)] md:w-[280px]"
                            unoptimized
                            priority
                        />
                    ) : null}
                    {damageFloater ? (
                        <BattleFloatingNumber key={damageFloater.key} value={damageFloater.value} />
                    ) : null}
                </div>
                
            </div>
        </div>
        
    );
};
