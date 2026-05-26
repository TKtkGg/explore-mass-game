type Props = {
    children: React.ReactNode;
}

export const Title = (props: Props) => {
    const { children } = props;
    return (
        <h1 className="font-black text-white sm:tracking-[0.08em] text-4xl sm:text-6xl md:text-7xl text-outline">
            {children}
        </h1>
    )
}