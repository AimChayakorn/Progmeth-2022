package logic.skill;

import logic.fighter.Fighter;
import logic.pokemua.Pokemua;
import serial.Serialize;
import serial.Serializer;

public abstract class Skill implements Serialize {

    /**
     * ID of skill
     */
    private int id;

    /**
     * Name of skill
     */
    private String skillName;

    /**
     * Owner of skill
     */
    private Pokemua skillOwner;

    /**
     * Cooldown of skill
     */
    private int cooldown;

    /**
     * Last used of skill
     */
    private int lastUsed;

    /**
     * Initialize skill
     */
    public Skill(int id, Pokemua skillOwner, String skillName, int cooldown) {
        this.skillName = skillName;
        this.cooldown = cooldown;
        this.lastUsed = 0;
        this.skillOwner = skillOwner;
        this.id = id;
    }

    /**
     * Initialize skill from template
     */
    public Skill(Pokemua skillOwner, SkillTemplate template) {
        lastUsed = 0;
        this.skillOwner = skillOwner;

    }

    /**
     * Get name of skill
     */
    public String getSkillName() {
        return this.skillName;
    }

    /**
     * Get cooldown of skill
     */
    public int getCoolDown() {
        return this.cooldown;
    }

    /**
     * Get last used of skill
     */
    public int getLastUsed() {
        return lastUsed;
    }

    /**
     * Set last used of skill
     */
    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }

    /**
     * Check if skill is available
     */
    public boolean isAvailable(int currentTick) {
        return currentTick - lastUsed >= cooldown;
    }

    /**
     * Get owner of skill
     */
    public Pokemua getSkillOwner() {
        return skillOwner;
    }

    /**
     * Get ID of skill
     */
    public int getId() {
        return id;
    }

    /**
     * Use the skill
     */
    public abstract void use(Fighter target);

    /**
     * Get the type of the skill
     */
    public abstract Type getType();

    /**
     * Serialize the skill
     */
    @Override
    public void serialize(Serializer serializer) {
        serializer.serialize(getId());
    }

    /**
     * Type of skill
     */
    public enum Type {
        ATTACK,
    }
}
