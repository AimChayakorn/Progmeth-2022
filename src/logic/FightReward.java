package logic;

import logic.fighter.Fighter;
import logic.pokemua.Pokemua;

import java.util.ArrayList;

public class FightReward {

    /**
     * Chance of dropping Pokemua
     */
    private static final double POKEMUA_DROP_CHANCE = 0.05;

    /**
     * Experience that will be added
     */
    private int experience;
    /**
     * List of all Pokemuas
     */
    private ArrayList<Pokemua> pokemuas;

    /**
     * Initialize experience and list of Pokemuas
     */
    public FightReward() {
        experience = 0;
        pokemuas = new ArrayList<>();
    }

    /**
     * Add experience and add new Pokemua
     */
    public void addReward(Fighter fighter) {
        fighter.getAllPokemuas().forEach(pokemua -> {
            experience += Math.pow(pokemua.getLevel(), 1.5) * 15;
            if (Math.random() < POKEMUA_DROP_CHANCE) {
                pokemua.setOwner(null);
                pokemuas.add(pokemua);
            }
        });
    }

    /**
     * Get all Pokemuas
     */
    public ArrayList<Pokemua> getPokemuas() {
        return pokemuas;
    }

    /**
     * Get experience
     */
    public int getExperience() {
        return experience;
    }
}
