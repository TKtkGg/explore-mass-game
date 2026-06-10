type Props = {
    firstRow: string;
    secondRow: string;
}

export const TwoRowTitle = (props: Props) => {
    const { firstRow, secondRow } = props;
    return (
        <header className="pt-[6vh] text-center sm:pt-[8vh]">
            <h1 className="font-black leading-tight text-white text-outline text-5xl sm:text-7xl md:text-8xl">
                <span className="block">{firstRow}</span>
                <span className="block">{secondRow}</span>
            </h1>
        </header>
    )
}