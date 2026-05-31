type Props = {
    message: string;
};

function splitMessageBySentence(message: string): string[] {
    if (!message.trim()) return [];
    return message
        .split(/(?<=[。！？!?])\s*/u)
        .map((line) => line.trim())
        .filter(Boolean);
}

export const BattleMessageBox = (props: Props) => {
    const { message } = props;
    const lines = splitMessageBySentence(message);

    if (lines.length === 0) return null;

    return (
        <div className="absolute left-4 top-4 z-20 max-w-[min(90vw,320px)] rounded-lg border-2 border-black/20 bg-white/85 px-4 py-3 shadow-[4px_4px_0_rgba(0,0,0,0.25)] sm:left-6 sm:top-6 sm:max-w-sm">
            {lines.map((line, index) => (
                <p
                    key={`${index}-${line}`}
                    className="text-sm font-bold leading-relaxed text-neutral-900 sm:text-base"
                >
                    {line}
                </p>
            ))}
        </div>
    );
};
