package logic.fighter;

import logic.Fight;
import logic.Player;
import logic.pokemua.Pokemua;

import java.util.ArrayList;
import java.util.List;

public class EngagedPlayer implements Fighter {

    /**
     * Current Pokemua
     */
    private Pokemua currentPokemua;

    /**
     * Player in the game
     */
    private Player player;

    /**
     * List of all selected Pokemuas
     */
    private ArrayList<Pokemua> selectedPokemua;

    /**
     * Fight after play new fight
     */
    private Fight fight;

    /**
     * Initialize engaged player
     */
    public EngagedPlayer(Player player, ArrayList<Pokemua> selectedPokemua, Fight fight) {
        this.selectedPokemua = selectedPokemua;
        this.fight = fight;
        this.player = player;
        currentPokemua = selectedPokemua.get(0);
    }

    /**
     * Get the selected Pokemuas
     */
    public ArrayList<Pokemua> getSelectedPokemua() {
        return selectedPokemua;
    }

    /**
     * Set the selected Pokemuas
     */
    public void setSelectedPokemua(ArrayList<Pokemua> pokemuas) {
        this.selectedPokemua = pokemuas;
    }

    /**
     * Get player level
     */
    public int getPlayerLevel() {
        return player.getLevel();
    }

    /**
     * Get current Pokemua
     */
    public Pokemua getCurrentPokemua() {
        return currentPokemua;
    }

    /**
     * Set current Pokemua
     */
    public void setCurrentPokemua(int index) {
        currentPokemua = selectedPokemua.get(index);
    }

    /**
     * Get all Pokemuas
     */
    public List<Pokemua> getAllPokemuas() {
        return selectedPokemua.stream().toList();
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
        return "Player";
    }

    /**
     * Get the player
     */
    public Player getPlayer() {
        return player;
    }
}
