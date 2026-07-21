"use client";

import { useState, type FormEvent, useEffect } from "react";
import { apiPost, apiGet } from "@/lib/apiClient";

export default function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<Error | null>(null);

    useEffect(() => {
        const fetchUser = async () => {
            const user = await apiGet("/auth/user");
            if (user.authenticated) {
                console.log(user)
            } else {
                console.log("not authenticated");
            }
        }
        fetchUser();
    }, []);

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await apiPost("/auth/login", { email, password });
        } catch (error) {
            if (error instanceof Error) {
                setError(error);
            } else {
                setError(new Error("通信に失敗しました。"));
            }
        }
    }

    const logout = async () => {
        await apiPost("/auth/logout");
    }

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">Email</label>
                    <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button type="submit">Login</button>
            </form>
            {error && <p>{error.message}</p>}

            <button onClick={logout}>Logout</button>
        </div>
    )
}