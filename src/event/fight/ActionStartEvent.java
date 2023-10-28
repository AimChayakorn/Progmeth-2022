package event.fight;

import logic.fighter.Fighter;
import logic.skill.Skill;

public class ActionStartEvent extends FightEvent {

    /**
     * Attacker
     */
    private Fighter attacker;

    /**
     * Target
     */
    private Fighter target;

    /**
     * Skill that used
     */
    private Skill skill;

    /**
     * Initialize event
     */
    public ActionStartEvent(Fighter attacker, Fighter target, Skill skill) {
        this.attacker = attacker;
        this.target = target;
        this.skill = skill;
    }

    /**
     * Get attacker
     */
    public Fighter getAttacker() {
        return attacker;
    }

    /**
     * Set attacker
     */
    public void setAttacker(Fighter attacker) {
        this.attacker = attacker;
    }

    /**
     * Get target
     */
    public Fighter getTarget() {
        return target;
    }

    /**
     * Set target
     */
    public void setTarget(Fighter target) {
        this.target = target;
    }

    /**
     * Get skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Set skill
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Get event type
     */
    public Type getType() {
        return Type.ACTION_START;
    }
}
