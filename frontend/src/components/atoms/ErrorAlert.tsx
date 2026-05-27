type Props = {
    message: string;
}

export const ErrorAlert = (props: Props) => {
    const { message } = props;
    return (
        <p
            className="mx-4 mt-2 rounded-md border-2 border-red-700 bg-red-950/90 px-3 py-2 text-center text-sm font-bold text-red-100"
            role="alert"
        >
            {message}
        </p>
    )
}