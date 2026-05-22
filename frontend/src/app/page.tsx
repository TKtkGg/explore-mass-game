"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiPost } from "@/lib/apiClient";

export default function Home() {
  const router = useRouter();
  const [name, setName] = useState("");
  const [error, setError] = useState<string | null>(null);

  const handleStart = async () => {
    if(name.length === 0) {
      setError("名前を入力してください。");
      return;
    }
    try {
      await apiPost("/start", { name });
      router.push("/explore");
    } catch (error: unknown) {
      if(error instanceof Error) {
        setError(error.message);
      } else {
        setError("通信に失敗しました。");
      }
    }
    
  };

  return (
    <>
      <h1>Explore game</h1>
      <input type="text" placeholder="Enter your name" value={name} onChange={(e) => setName(e.target.value)} />
      <button onClick={() => handleStart()}>START</button>
      {error && <p>{error}</p>}
    </>
  );
}
