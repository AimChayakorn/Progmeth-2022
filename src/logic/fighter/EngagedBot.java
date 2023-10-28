package logic.fighter;

import logic.Fight;
import logic.pokemua.Pokemua;

import java.util.ArrayList;
import java.util.List;

public class EngagedBot implements Fighter {

    /**
     * Name of the bot
     */
    private String botName;

    /**
     * Current Pokemua
     */
    private Pokemua currentPokemua;

    /**
     * List of all selected Pokemuas
     */
    private ArrayList<Pokemua> selectedPokemua;

    /**
     * To check if the bot is dead
     */
    private boolean isDead;

    /**
     * Fight after play new fight
     */
    private Fight fight;

    /**
     * Initialize the bot
     */
    public EngagedBot(String botName, ArrayList<Pokemua> selectedPokemua, Fight fight) {
        this.botName = botName;
        this.selectedPokemua = selectedPokemua;
        this.currentPokemua = selectedPokemua.get(0);
        this.fight = fight;
    }

    /**
     * Get the bot name
     */
    public String getBotName() {
        return botName;
    }

    /**
     * Set the bot name
     */
    public void setBotName(String botName) {
        this.botName = botName;
    }

    /**
     * Get the current pokemua
     */
    public ArrayList<Pokemua> getSelectedPokemua() {
        return selectedPokemua;
    }

    /**
     * Get the current pokemua
     */
    public Pokemua getCurrentPokemua() {
        return currentPokemua;
    }

    /**
     * Set the current pokemua
     */
    public void setCurrentPokemua(int index) {
        currentPokemua = selectedPokemua.get(index);
    }

    /**
     * Get all pokemuas
     */
    public List<Pokemua> getAllPokemuas() {
        return selectedPokemua.stream().toList();
    }

    /**
     * Check if the bot is dead
     */
    public boolean getDead() {
        return isDead;
    }

    /**
     * Set the bot dead
     */
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    /**
     * Get the fight
     */
    public Fight getFight() {
        return fight;
    }

    /**
     * Get the bot name
     */
    public String getDisplayName() {
        return botName;
    }
}
