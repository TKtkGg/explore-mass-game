type Props = {
    title: string;
    lines: string;
    x: number;
    y: number;
    wide?: boolean;
}

export const Tooltip = (props: Props) => {
    const { title, lines, x, y, wide } = props;

    return (
        <div
            className="pointer-events-none fixed z-50 max-w-xs border-2 bg-black/90 px-4 py-3 shadow-[4px_4px_0_rgba(0,0,0,0.4)] sm:max-w-sm"
            style={{ left: x + 16, top: y + 16 }}
            role="tooltip"
        >
            <p className="text-xl font-black text-white text-outline sm:text-2xl">
                {title}
            </p>
            <p className={`mt-1 text-white ${wide ? "text-sm font-bold leading-relaxed sm:text-base" : "text-lg font-black text-outline sm:text-xl"}`}>
                {lines}
            </p>
        </div>
    );
}