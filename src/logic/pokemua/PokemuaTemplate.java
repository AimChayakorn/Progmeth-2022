package logic.pokemua;

import logic.skill.SkillLoader;
import logic.skill.SkillTemplate;

public class PokemuaTemplate {

    /**
     * Name of pokemua
     */
    private String name;

    /**
     * ID of pokemua
     */
    private int id;

    /**
     * Max HP of pokemua
     */
    private int maxHp;

    /**
     * Attack of pokemua
     */
    private int attack;

    /**
     * Defense of pokemua
     */
    private int defense;

    /**
     * Image URI of pokemua
     */
    private String imageURI;

    /**
     * List of all entry skills of pokemua
     */
    private SkillEntry[] skillEntries;

    /**
     * Initialize pokemua template
     */
    public PokemuaTemplate(int id, String name, int maxHp, int attack, int defense, String imageURI, SkillEntry[] skillEntries) {
        this.name = name;
        this.id = id;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.imageURI = imageURI;
        this.skillEntries = skillEntries;
    }

    /**
     * Initialize pokemua template with no entry skills
     */
    public PokemuaTemplate(int id, String name, int maxHp, int attack, int defense, String imageURI) {
        this.name = name;
        this.id = id;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.imageURI = imageURI;
    }

    /**
     * Get Pokemua name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Pokemua name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Pokemua ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set Pokemua ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Pokemua max HP
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Set Pokemua max HP
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Get Pokemua attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Set Pokemua attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Get Pokemua defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Set Pokemua defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Get Pokemua image URI
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * Set Pokemua image URI
     */
    public void setImageURL(String imageURI) {
        this.imageURI = imageURI;
    }

    /**
     * Get Pokemua entry skills
     */
    public SkillEntry[] getSkillEntries() {
        return skillEntries;
    }

    /**
     * Class of entry skill of Pokemua
     */
    public static class SkillEntry {

        /**
         * Accquire level
         */
        public int accquireLevel;

        /**
         * Skill template
         */
        public SkillTemplate template;

        /**
         * Initialize skill entry
         */
        public SkillEntry(int accquireLevel, int skillTemplateIndex) {
            this.accquireLevel = accquireLevel;
            this.template = SkillLoader.load(skillTemplateIndex);
        }

        /**
         * Get accquire level
         */
        public int getAccquireLevel() {
            return accquireLevel;
        }

        /**
         * Get the skill template
         */
        public SkillTemplate getTemplate() {
            return template;
        }
    }
}
