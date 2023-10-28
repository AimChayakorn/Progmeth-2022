package logic.fighter;

import logic.Fight;
import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaRandomizer;

import java.util.ArrayList;
import java.util.List;

public class WildPokemua implements Fighter {

    /**
     * The Pokemua
     */
    private Pokemua pokemua;

    /**
     * Fight in the game
     */
    private Fight fight;

    /**
     * Initialize wild Pokemua
     */
    public WildPokemua(Fight fight) {
        int playerPokemonLevel = 0;
        for (Pokemua p : fight.getEngagedPlayer().getAllPokemuas()) {
            playerPokemonLevel += p.getLevel();
        };

        int minPokemuaLevel = Math.max(1, playerPokemonLevel / fight.getEngagedPlayer().getAllPokemuas().size() - 30);
        int maxPokemuaLevel = playerPokemonLevel / fight.getEngagedPlayer().getAllPokemuas().size() + 30;

        this.pokemua = PokemuaRandomizer.random(minPokemuaLevel, maxPokemuaLevel);
        this.fight = fight;
    }

    /**
     * Initialize wild Pokemua with the Pokemua
     */
    public WildPokemua(Pokemua pokemua, Fight fight) {
        this.pokemua = pokemua;
        this.fight = fight;
    }

    /**
     * Get the current Pokemua
     */
    public Pokemua getCurrentPokemua() {
        return pokemua;
    }

    /**
     * Set the current Pokemua
     */
    public void setCurrentPokemua(int index) {
    }

    /**
     * Get all Pokemuas
     */
    public List<Pokemua> getAllPokemuas() {
        List<Pokemua> p = new ArrayList<>();
        p.add(pokemua);
        return p;
    }

    /**
     * Get the fight
     */
    public Fight getFight() {
        return fight;
    }

    /**
     * Get the name
     */
    public String getDisplayName() {
        return "Wild " + pokemua.getName();
    }
}
