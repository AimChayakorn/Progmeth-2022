package event.fight;

import event.Event;

public abstract class FightEvent extends Event {

    /**
     * Initialize event
     */
    public FightEvent() {
    }

    /**
     * Get event type
     */
    public abstract Type getType();

    /**
     * Event type
     */
    public enum Type {
        TICK,
        ACTION_START,
        ACTION_END,
        CHANGE_POKEMUA,
        FIGHTER_DEAD_EVENT,
        FIGHT_END,
        PLAYER_ATTACKED,
    }
}
