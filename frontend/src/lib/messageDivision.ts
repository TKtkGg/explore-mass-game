export const messageDivision = (message: string, isPlayerFast: boolean, playerName: string, enemyName: string) => {
    let messages: string[] = [];
    let breakPoint: number;
    if (isPlayerFast) {
        breakPoint = message.indexOf(enemyName);
        messages = [message.substring(0, breakPoint), message.substring(breakPoint)];
    } else {
        breakPoint = message.indexOf(playerName);
        messages = [message.substring(0, breakPoint), message.substring(breakPoint)];
    }
    if (breakPoint <= 0) {
        return [message];
    }
    return messages;
}