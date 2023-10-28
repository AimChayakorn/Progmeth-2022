package logic.skill;

import logic.fighter.Fighter;
import logic.pokemua.Pokemua;

public class AttackSkill extends Skill {

    /**
     * Base damage of the skill
     */
    private int baseDamage;

    /**
     * Initialize attack skill
     */
    public AttackSkill(int id, Pokemua skillOwner, String skillName, int cooldown, int baseDamage) {
        super(id, skillOwner, skillName, cooldown);
        this.baseDamage = baseDamage;
    }

    /**
     * Get base damage of the skill
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Use the skill
     */
    public void use(Fighter target) {
        Pokemua targetPokemua = target.getCurrentPokemua();
        double defense = targetPokemua.getDefense();
        double attack = getSkillOwner().getAttack();
        int damage = (int) ((attack - defense / 10) * baseDamage / 100);
        targetPokemua.setHp(targetPokemua.getHp() - damage);
    }

    /**
     * Get the type of the skill
     */
    public Type getType() {
        return Type.ATTACK;
    }
}
