package controllers;

import javafx.stage.Stage;
import logic.Game;

public class Controller {

    /**
     * Game instance
     */
    private Game instance;

    /**
     * Primary stage
     */
    private Stage primaryStage;

    /**
     * Game controller
     */
    private GameController gameController;

    /**
     * Player controller
     */
    private PlayerController playerController;

    /**
     * Fight controller
     */
    private FightController fightController;

    /**
     * Pokemua controller
     */
    private PokemuaController pokemuaController;

    /**
     * UI
     */
    private Object ui;

    /**
     * Initialize controller
     */
    public Controller(Game instance, Stage primaryStage) {
        this.instance = instance;
        this.primaryStage = primaryStage;

        gameController = new GameController(this);
        playerController = new PlayerController(this);
        fightController = new FightController(this);
        pokemuaController = new PokemuaController(this);
    }

    /**
     * Get game instance
     */
    public Game getGameInstance() {
        return instance;
    }

    /**
     * Get primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Get game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Get player controller
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Get fight controller
     */
    public FightController getFightController() {
        return fightController;
    }

    /**
     * Get pokemua controller
     */
    public PokemuaController getPokemuaController() {
        return pokemuaController;
    }

    /**
     * Get UI
     */
    public Object getUI() {
        return ui;
    }

    /**
     * Set UI
     */
    public void setUI(Object ui) {
        this.ui = ui;
    }
}
