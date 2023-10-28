package event.fight;

import logic.fighter.Fighter;

public class FighterDeadEvent extends FightEvent {

    /**
     * Fighter in the event
     */
    private Fighter fighter;

    /**
     * Initialize event
     */
    public FighterDeadEvent(Fighter fighter) {
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
    @Override
    public Type getType() {
        return Type.FIGHTER_DEAD_EVENT;
    }
}
