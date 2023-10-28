package logic.skill;

import logic.pokemua.Pokemua;
import serial.Deserializer;

public abstract class SkillTemplate {

    /**
     * Name of skill
     */
    private String name;

    /**
     * Cooldown of skill
     */
    private int cooldown;

    /**
     * ID of skill
     */
    private int id;

    /**
     * Initialize skill template
     */
    public SkillTemplate(int id, String name, int cooldown) {
        this.id = id;
        this.name = name;
        this.cooldown = cooldown;
    }

    /**
     * Deserialize skill from deserializer
     */
    public static SkillTemplate deserialize(Deserializer deserializer) {
        int id = deserializer.deserializeInt();

        return SkillLoader.load(id);
    }

    /**
     * Get name of skill
     */
    public String getName() {
        return name;
    }

    /**
     * Get cooldown of skill
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Get ID of skill
     */
    public int getId() {
        return id;
    }

    /**
     * Get type of skill
     */
    public abstract Skill.Type getType();

    /**
     * Convert skill template to skill
     */
    public abstract Skill toSkill(Pokemua skillOwner);
}
