type Props = {
    children: React.ReactNode;
    onClick: () => void;
}

export const MainButton = (props: Props) => {
    const { children, onClick } = props;
    return (
        <button
            type="button"
            onClick={onClick}
            className={`min-w-[200px] rounded-md border-2 border-black bg-white px-8 py-3 text-lg font-black tracking-widest text-neutral-900 shadow-[4px_4px_0_#000] transition cursor-pointer hover:bg-neutral-100 active:translate-y-0.5 active:shadow-none sm:min-w-[240px] sm:text-xl`}
        >
            {children}
        </button>
    )
}