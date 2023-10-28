package action;

import logic.fighter.Fighter;
import logic.skill.Skill;

public class UseSkillAction extends Action {

    /**
     * Fighter that use skill
     */
    private Fighter attacker;

    /**
     * Fighter that get damage
     */
    private Fighter target;

    /**
     * Skill that fighter use
     */
    private Skill skill;

    /**
     * Initialize use skill action
     */
    public UseSkillAction(Fighter attacker, Fighter target, Skill skill) {
        this.attacker = attacker;
        this.target = target;
        this.skill = skill;
    }

    /**
     * Get fighter that use skill
     */
    public Fighter getAttacker() {
        return attacker;
    }

    /**
     * Set fighter that use skill
     */
    public void setAttacker(Fighter attacker) {
        this.attacker = attacker;
    }

    /**
     * Get fighter that get damage
     */
    public Fighter getTarget() {
        return target;
    }

    /**
     * Set fighter that get damage
     */
    public void setTarget(Fighter target) {
        this.target = target;
    }

    /**
     * Get skill that fighter use
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Set skill that fighter use
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Change action to string
     */
    @Override
    public String toString() {
        return "UseSkillAction{" +
                "attacker=" + attacker +
                ", target=" + target +
                ", skill=" + skill +
                '}';
    }

    /**
     * Get the type of the action
     */
    public Type getType() {
        return Type.USE_SKILL;
    }
}
