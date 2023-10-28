package logic.pokemua;

import logic.Player;
import logic.skill.Skill;
import logic.skill.SkillTemplate;
import serial.Deserializer;
import serial.Serialize;
import serial.Serializer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Pokemua implements Serialize {

    /**
     * ID of pokemua
     */
    private final int id;

    /**
     * Name of pokemua
     */
    private String name;

    /**
     * HP of pokemua
     */
    private int hp;

    /**
     * List of all skills of pokemua
     */
    private ArrayList<Skill> skills;

    /**
     * List of all pending skills of pokemua
     */
    private ArrayList<Skill> pendingSkills;

    /**
     * Level of pokemua
     */
    private int level;

    /**
     * Stat of pokemua
     */
    private PokemuaStat stat;

    /**
     * Image URI of pokemua
     */
    private String imageURI;

    /**
     * Experience of pokemua
     */
    private int experience;

    /**
     * Required experience that use to get level up
     */
    private int requiredExperience;

    /**
     * Owner of pokemua
     */
    private Player owner;

    /**
     * Initialize pokemua from template and set the level
     */
    public Pokemua(PokemuaTemplate template, int level) {
        this.id = template.getId();
        this.name = template.getName();
        this.stat = new PokemuaStat(template, level);
        this.hp = this.stat.getMaxHp();
        this.level = level;
        this.imageURI = template.getImageURI();
        this.skills = new ArrayList<>();
        this.pendingSkills = new ArrayList<>();
        this.requiredExperience = (level + 1) * (level + 1) * 10;
    }

    /**
     * Deserialize information from deserializer and set to Pokemua
     */
    public static Pokemua deserialize(Deserializer deserializer) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        int id = deserializer.deserializeInt();
        int level = deserializer.deserializeInt();
        int experience = deserializer.deserializeInt();

        Pokemua p = PokemuaLoader.load(id, level);
        p.getSkills().clear();

        ArrayList<SkillTemplate> skills = deserializer.deserializeList(SkillTemplate.class);
        skills.forEach(skillTemplate -> {
            p.learnSkill(skillTemplate);
        });

        ArrayList<SkillTemplate> pendingSkills = deserializer.deserializeList(SkillTemplate.class);
        pendingSkills.forEach(skillTemplate -> {
            p.learnSkill(skillTemplate);
        });

        p.addExperience(experience);

        return p;
    }

    /**
     * Get Pokemua HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set Pokemua HP
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Get Pokemua name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set Pokemua name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Pokemua attack
     */
    public int getAttack() {
        return this.stat.getAttack();
    }

    /**
     * Get Pokemua defense
     */
    public int getDefense() {
        return this.stat.getDefense();
    }

    /**
     * Get all Pokemua skills
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Set Pokemua skills
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Get Pokemua level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set Pokemua level
     */
    public void setLevel(int level) {
        stat.setLevel(level);
        this.level = level;
        hp = stat.getMaxHp();
    }

    /**
     * Get Pokemua max HP
     */
    public int getMaxHp() {
        return stat.getMaxHp();
    }

    /**
     * Get Pokemua experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Get Pokemua required experience
     */
    public int getRequiredExperience() {
        return requiredExperience;
    }

    /**
     * Get pokemua pending skills
     */
    public ArrayList<Skill> getPendingSkills() {
        return pendingSkills;
    }

    /**
     * Check if Pokemua has pending skill
     */
    public boolean hasPendingSkill() {
        return pendingSkills.size() > 0;
    }

    /**
     * Learn new skill
     */
    public void learnSkill(Skill skill) {
        if (skills.size() < 4) {
            skills.add(skill);
        } else {
            pendingSkills.add(skill);
        }
    }

    /**
     * Learn new skill from template
     */
    public void learnSkill(SkillTemplate skillTemplate) {
        learnSkill(skillTemplate.toSkill(this));
    }

    /**
     * Unlearn skill
     */
    public void unlearnSkill(Skill skill) {
        if (skills.contains(skill)) {
            skills.remove(skill);
            if (pendingSkills.size() > 0) {
                skills.add(pendingSkills.remove(0));
            }
        } else if (pendingSkills.contains(skill)) {
            pendingSkills.remove(skill);
        }
    }

    /**
     * Check if Pokemua is dead
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Get Pokemua Image URI
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * Set Pokemua Image URI
     */
    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    /**
     * Add experience to Pokemua
     */
    public boolean addExperience(int addedExperience) {
        double multiplier;
        if (owner != null) {
            multiplier = owner.getLevelMultiplier();
        } else {
            multiplier = 1;
        }
        experience += (int) (addedExperience * multiplier);

        boolean ret = false;

        while (experience >= requiredExperience) {
            setLevel(level + 1);
            experience -= requiredExperience;
            requiredExperience = (level + 1) * (level + 1) * 10;
            ret = true;
        }
        return ret;
    }

    /**
     * Get Pokemua owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set Pokemua owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Get Pokemua ID
     */
    public int getId() {
        return id;
    }

    /**
     * Reset Pokemua stats
     */
    public void reset() {
        hp = stat.getMaxHp();
        skills.forEach(skill -> skill.setLastUsed(0));
    }

    /**
     * Get Pokemua stat
     */
    public PokemuaStat getStat() {
        return stat;
    }

    /**
     * Serialize Pokemua information
     */
    @Override
    public void serialize(Serializer serializer) {
        serializer.serialize(id);
        serializer.serialize(level);
        serializer.serialize(experience);
        serializer.serialize(skills);
        serializer.serialize(pendingSkills);
    }
}
