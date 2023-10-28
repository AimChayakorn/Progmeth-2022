package event.fight;

import logic.fighter.Fighter;

public class ChangePokemuaEvent extends FightEvent {

    /**
     * Fighter in the event
     */
    private Fighter fighter;

    /**
     * Initialize event
     */
    public ChangePokemuaEvent(Fighter fighter) {
        this.fighter = fighter;
    }

    /**
     * Get fighter
     */
    public Fighter getFighter() {
        return fighter;
    }

    /**
     * Set fighter
     */
    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    /**
     * Get event type
     */
    public Type getType() {
        return Type.CHANGE_POKEMUA;
    }
}
