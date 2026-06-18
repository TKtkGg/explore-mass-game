import Image from "next/image";
import { BattleEffect, EnemyState } from "@/type/types";
import { HpBar } from "@/components/atoms/HpBar";
import { BattleFloatingNumber } from "../atoms/BattleFloatingNumber";
import { BattleSpriteEffect } from "../atoms/BattleSpriteEffect";
import { EFFECT } from "@/lib/effectPaths";

type Props = {
    enemy: EnemyState | undefined;
    hp: number;
    isShaking?: boolean;
    shakeKey?: number;
    damageFloater?: { value: number; key: number } | null;
    effect?: BattleEffect | null;
};

export const BattleEnemyDisplay = (props: Props) => {
    const { enemy, hp, isShaking, shakeKey, damageFloater, effect } = props;

    if (!enemy) return null;

    return (
            <div className="flex w-full max-w-md flex-col items-center gap-3 px-4 sm:max-w-lg sm:gap-4">
                <p className="text-lg font-black text-white text-outline sm:text-xl md:text-2xl">
                    Lv.{enemy.level} {enemy.name}
                </p>
                <HpBar hp={hp} maxHp={enemy.maxHp} className="max-w-xs sm:max-w-sm" />
                <div className="relative">
                    {enemy.imagePath ? (
                        <div key={shakeKey} className={isShaking ? "animate-battle-shake" : ""}>
                            <Image
                                src={enemy.imagePath}
                                alt={enemy.name}
                                width={240}
                                height={240}
                                className={[
                                    "h-auto w-[min(55vw,240px)] object-contain drop-shadow-[0_8px_24px_rgba(0,0,0,0.35)] sm:w-[min(45vw,280px)] md:w-[280px]",
                                    effect?.target === "enemy" && effect?.kind === "flash" && effect.type === "hit"
                                    ? "animate-battle-image-flash-hit"
                                    : "",
                                    effect?.target === "enemy" && effect?.kind === "flash" && effect.type === "defend"
                                    ? "animate-battle-image-flash-defend"
                                    : "",
                                ].join(" ")}
                                unoptimized
                                priority
                            />
                        </div>
                        
                    ) : null}

                    {effect?.target === "enemy" && effect?.kind === "sprite" ? (
                        <BattleSpriteEffect
                            key={`sprite-${effect.key}`}
                            src={EFFECT[effect.type]}
                        />
                    ) : null}
                    {damageFloater ? (
                        <BattleFloatingNumber key={`damage-${damageFloater.key}`} value={damageFloater.value} />
                    ) : null}
                </div>
                
            </div>
        
    );
};
