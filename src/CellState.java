
/**
 * Ï¸°û×´Ì¬
 */
public enum CellState {
    DEAD(0),
    LIVE(1);
    private int value;

    CellState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}