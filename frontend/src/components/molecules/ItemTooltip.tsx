import { getItemHealAmount } from "@/lib/itemHealAmount";
import { OwnedItemDisplay } from "@/components/molecules/ItemDisplay";

type Props = {
    item: OwnedItemDisplay;
    x: number;
    y: number;
};

export const ItemTooltip = (props: Props) => {
    const { item, x, y } = props;
    const healAmount = getItemHealAmount(item.name);

    return (
        <div
            className="pointer-events-none fixed z-50 border-2 bg-black/90 px-4 py-3 shadow-[4px_4px_0_rgba(0,0,0,0.4)]"
            style={{ left: x + 16, top: y + 16 }}
            role="tooltip"
        >
            <p className="text-xl font-black text-white text-outline sm:text-2xl">
                {item.name}
            </p>
            <p className="mt-1 text-lg font-black text-white text-outline sm:text-xl">
                {healAmount !== null ? `HP : ${healAmount}` : "HP : —"}
            </p>
        </div>
    );
};
