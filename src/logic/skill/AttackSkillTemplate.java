package logic.skill;

import logic.pokemua.Pokemua;

public class AttackSkillTemplate extends SkillTemplate {

    /**
     * Base damage of attack skill
     */
    private int baseDamage;

    /**
     * Initialize attack skill template
     */
    public AttackSkillTemplate(int id, String name, int baseDamage, int cooldown) {
        super(id, name, cooldown);
        this.baseDamage = baseDamage;
    }

    /**
     * Convert attack skill template to attack skill
     */
    public Skill toSkill(Pokemua skillOwner) {
        return new AttackSkill(getId(), skillOwner, getName(), getBaseDamage(), getBaseDamage());
    }

    /**
     * Get base damage of the skill
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Get the type of the skill
     */
    @Override
    public Skill.Type getType() {
        return Skill.Type.ATTACK;
    }
}
