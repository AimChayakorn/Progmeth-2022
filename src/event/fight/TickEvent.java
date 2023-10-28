package event.fight;

public class TickEvent extends FightEvent {

    /**
     * Current tick
     */
    private int currentTick;

    /**
     * Initialize event
     */
    public TickEvent(int currentTick) {
        this.currentTick = currentTick;
    }

    /**
     * Get current tick
     */
    public int getCurrentTick() {
        return currentTick;
    }

    /**
     * Set current tick
     */
    public void setCurrentTick(int currentTick) {
        this.currentTick = currentTick;
    }

    /**
     * Get event type
     */
    public Type getType() {
        return Type.TICK;
    }
}
