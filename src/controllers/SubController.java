package controllers;

import logic.Game;

public abstract class SubController {

    /**
     * Parent controller
     */
    private Controller parent;

    /**
     * Initialize sub controller
     */
    public SubController(Controller parent) {
        this.parent = parent;
    }

    /**
     * Get game instance
     */
    public Game getGameInstance() {
        return parent.getGameInstance();
    }

    /**
     * Get the controller
     */
    public Controller getController() {
        return parent;
    }
}
