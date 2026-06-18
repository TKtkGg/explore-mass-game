export const EFFECT = {
    slash: "/effects/slash.mp3",
    punch: "/effects/punch.mp3",
} as const;

export type FlashType = "hit" | "defend" | "heal";
export type SpriteEffectType = "slash" | "punch";