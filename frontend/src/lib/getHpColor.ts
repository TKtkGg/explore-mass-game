export function getHpColor(percent: number): string {
    if (percent >= 81) return "rgb(73, 254, 73)";
    if (percent >= 66) return "rgb(200, 254, 73)";
    if (percent >= 50) return "rgb(251, 254, 73)";
    if (percent >= 31) return "rgb(254, 191, 73)";
    return "rgb(255, 0, 0)";
}
