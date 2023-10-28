package event.player;

public class LevelUpEvent extends PlayerEvent {

    /**
     * New level
     */
    private int newLevel;

    /**
     * Initialize event
     */
    public LevelUpEvent(int newLevel) {
        super();
        this.newLevel = newLevel;
    }

    /**
     * Get new level
     */
    public int getNewLevel() {
        return newLevel;
    }
}
