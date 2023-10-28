package logic;

import logic.pokemua.Pokemua;
import serial.Deserializer;
import serial.Serialize;
import serial.Serializer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Player implements Serialize {

    /**
     * Level of player
     */
    private int level;

    /**
     * List of all Pokemua of player
     */
    private ArrayList<Pokemua> pokemuas;

    /**
     * Experience of player
     */
    private int experience;

    /**
     * Required experience that use to get level up
     */
    private int requiredExperience;

    /**
     * -Set player level to 1
     * -Set required experience
     * -Initialize list of Pokemua
     */
    public Player() {
        this.level = 1;
        this.requiredExperience = (level + 1) * (level + 1) * 15;
        pokemuas = new ArrayList<>();
    }

    /**
     * Deserialize information from deserializer and set to player
     */
    public static Player deserialize(Deserializer deserializer) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        int level = deserializer.deserializeInt();
        int experience = deserializer.deserializeInt();
        ArrayList<Pokemua> pokemuas = deserializer.deserializeList(Pokemua.class);

        Player p = new Player();
        p.getPokemuas().clear();
        p.setLevel(level);
        p.addExperience(experience);

        for (Pokemua pokemua : pokemuas) {
            p.addPokemua(pokemua);
        }

        return p;
    }

    /**
     * Add level by 1
     */
    public void levelUp() {
        this.level += 1;
    }

    /**
     * Get level multiplier
     */
    public double getLevelMultiplier() {
        return 1 + level * 0.02;
    }

    /**
     * Add experience to player
     */
    public boolean addExperience(int addedExperience) {
        experience += addedExperience * getLevelMultiplier();

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
     * Get player's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set player's level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get all Pokemuas
     */
    public ArrayList<Pokemua> getPokemuas() {
        return pokemuas;
    }

    /**
     * Add new Pokemua and set the owner to player
     */
    public void addPokemua(Pokemua pokemua) {
        pokemuas.add(pokemua);
        pokemua.setOwner(this);
    }

    /**
     * Serialize player information
     */
    @Override
    public void serialize(Serializer serializer) {
        serializer.serialize(level);
        serializer.serialize(experience);
        serializer.serialize(pokemuas);
    }
}
