type Props = {
    placeholder: string;
    value: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    type?: "text" | "email" | "password";
    className?: string;
}

export const Input = (props: Props) => {
    const { placeholder, value, onChange, type = "text", className } = props;
    return (
        <input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            className={`w-full max-w-lg border-2 border-black bg-white px-4 py-3 text-lg font-bold text-neutral-900 placeholder:text-neutral-400 focus:outline-none focus:ring-2 focus:ring-neutral-400 sm:py-4 sm:text-xl ${className ?? ""}`}
        />
    )
}