package logic;

import logic.pokemua.Pokemua;

import java.util.ArrayList;

public class Game {

    /**
     * Player in the game
     */
    private Player player;
    /**
     * Fight after play new fight
     */
    private Fight fight;

    /**
     * Initialize the game
     */
    public Game() {
        this.initialize();
    }

    /**
     * Initialize the game with player
     */
    public Game(Player player) {
        this.player = player;
    }

    /**
     * Initialize game by initialize new player
     */
    public void initialize() {
        this.player = new Player();
    }

    /**
     * Get player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Initialize new fight
     */
    public void startGame(ArrayList<Pokemua> selectedPokemuas) {
        fight = new Fight(player, selectedPokemuas);
    }

    /**
     * Get the fight
     */
    public Fight getFight() {
        return fight;
    }
}
