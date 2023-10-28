package event.pokemua;

import event.Event;

public abstract class PokemuaEvent extends Event {

    /**
     * Initialize event
     */
    public PokemuaEvent() {
    }

    /**
     * Get event type
     */
    public abstract Type getType();

    /**
     * Event type
     */
    public enum Type {
        POKEMUA_SKILL_CHANGE,
    }
}
