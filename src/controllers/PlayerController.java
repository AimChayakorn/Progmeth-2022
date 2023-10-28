package controllers;

import event.player.LevelUpEvent;
import event.player.PlayerEvent;
import logic.Player;
import logic.pokemua.Pokemua;

import java.util.ArrayList;

public class PlayerController extends EventController<PlayerEvent> {

    /**
     * Initialize player controller
     */
    public PlayerController(Controller parent) {
        super(parent);
    }

    /**
     * Level up player
     */
    public void levelUp() {
        Player p = getGameInstance().getPlayer();
        p.levelUp();

        emit(new LevelUpEvent(p.getLevel()));
    }

    /**
     * Add pokemua to player
     */
    public void addPokemua(Pokemua p) {
        getGameInstance().getPlayer().addPokemua(p);
    }

    /**
     * Get player level
     */
    public int getPlayerLevel() {
        return getGameInstance().getPlayer().getLevel();
    }

    /**
     * Get player pokemuas
     */
    public ArrayList<Pokemua> getPokemuas() {
        Player p = getGameInstance().getPlayer();

        return p.getPokemuas();
    }
}
