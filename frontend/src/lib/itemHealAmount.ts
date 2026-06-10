/** ItemListState と同じ回復量（API が amount を返さないため参照用） */
const ITEM_HEAL_AMOUNT: Record<string, number> = {
    "回復薬(小)": 20,
    "回復薬(中)": 50,
    "回復薬(大)": 100,
};

export function getItemHealAmount(itemName: string): number | null {
    return ITEM_HEAL_AMOUNT[itemName] ?? null;
}
