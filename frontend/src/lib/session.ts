const SESSION_KEY = "sessionId";
const GAME_FINISHED_KEY = "gameFinished";

export const saveSessionId = (sessionId: string) => {
    localStorage.setItem(SESSION_KEY, sessionId);
};

export const getSessionId = (): string | null => {
    return localStorage.getItem(SESSION_KEY);
};

export const clearSessionId = () => {
    localStorage.removeItem(SESSION_KEY);
};

export const markGameFinished = () => localStorage.setItem(GAME_FINISHED_KEY, "true");
export const isGameFinished = () => localStorage.getItem(GAME_FINISHED_KEY) === "true";
export const clearGameFinished = () => localStorage.removeItem(GAME_FINISHED_KEY);