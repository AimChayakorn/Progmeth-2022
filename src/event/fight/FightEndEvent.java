package event.fight;

import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaStat;
import logic.skill.SkillTemplate;

import java.util.ArrayList;

public class FightEndEvent extends FightEvent {

    /**
     * Experience
     */
    private int experience;

    /**
     * Old player level
     */
    private int oldPlayerLevel;

    /**
     * New player level
     */
    private int newPlayerLevel;

    /**
     * List of pokemua level change
     */
    private ArrayList<PokemuaLevelChange> pokemuaLevelChanges;

    /**
     * List of new pokemuas
     */
    private ArrayList<Pokemua> newPokemuas;

    /**
     * Initialize fight end event
     */
    public FightEndEvent() {
        pokemuaLevelChanges = new ArrayList<>();
    }

    /**
     * Get the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Set the experience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Get the old player level
     */
    public int getOldPlayerLevel() {
        return oldPlayerLevel;
    }

    /**
     * Set the old player level
     */
    public void setOldPlayerLevel(int oldPlayerLevel) {
        this.oldPlayerLevel = oldPlayerLevel;
    }

    /**
     * Get the new player level
     */
    public int getNewPlayerLevel() {
        return newPlayerLevel;
    }

    /**
     * Set the new player level
     */
    public void setNewPlayerLevel(int newPlayerLevel) {
        this.newPlayerLevel = newPlayerLevel;
    }

    /**
     * Add pokemua level change
     */
    public void addPokemonLevelChange(PokemuaStat old, Pokemua pokemua, ArrayList<SkillTemplate> newSkills) {
        pokemuaLevelChanges.add(new PokemuaLevelChange(old, pokemua, newSkills));
    }

    /**
     * Check if player level up
     */
    public boolean playerLevelUp() {
        return oldPlayerLevel != newPlayerLevel;
    }

    /**
     * Get pokemua level changes
     */
    public ArrayList<PokemuaLevelChange> getPokemuaLevelChanges() {
        return pokemuaLevelChanges;
    }

    /**
     * Get new pokemuas
     */
    public ArrayList<Pokemua> getNewPokemuas() {
        return newPokemuas;
    }

    /**
     * Set new pokemuas
     */
    public void setNewPokemuas(ArrayList<Pokemua> newPokemuas) {
        this.newPokemuas = newPokemuas;
    }

    /**
     * Get event type
     */
    @Override
    public Type getType() {
        return Type.FIGHT_END;
    }

    /**
     * Class of pokemua level change
     */
    public class PokemuaLevelChange {

        /**
         * Old stat
         */
        PokemuaStat oldStat;

        /**
         * New stat
         */
        PokemuaStat newStat;

        /**
         * New skills
         */
        ArrayList<SkillTemplate> newSkills;

        /**
         * Pokemua
         */
        Pokemua pokemua;

        /**
         * Initialize pokemua level change
         */
        public PokemuaLevelChange(PokemuaStat oldStat, Pokemua pokemua, ArrayList<SkillTemplate> newSkills) {
            this.oldStat = oldStat;
            this.pokemua = pokemua;
            this.newSkills = newSkills;
            newStat = pokemua.getStat();
        }

        /**
         * Get old stat
         */
        public PokemuaStat getOldStat() {
            return oldStat;
        }

        /**
         * Set old stat
         */
        public void setOldStat(PokemuaStat oldStat) {
            this.oldStat = oldStat;
        }

        /**
         * Get new stat
         */
        public PokemuaStat getNewStat() {
            return newStat;
        }

        /**
         * Set new stat
         */
        public void setNewStat(PokemuaStat newStat) {
            this.newStat = newStat;
        }

        /**
         * Get pokemua
         */
        public Pokemua getPokemua() {
            return pokemua;
        }

        /**
         * Set pokemua
         */
        public void setPokemua(Pokemua pokemua) {
            this.pokemua = pokemua;
        }

        /**
         * Get new skills
         */
        public ArrayList<SkillTemplate> getNewSkills() {
            return newSkills;
        }

        /**
         * Set new skills
         */
        public void setNewSkills(ArrayList<SkillTemplate> newSkills) {
            this.newSkills = newSkills;
        }
    }
}
