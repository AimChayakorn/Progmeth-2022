package controllers;

import event.fight.FightEndEvent;
import javafx.scene.Scene;
import logic.pokemua.Pokemua;
import pane.*;
import serial.Serializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameController extends SubController {

    /**
     * Path to save game progress
     */
    private static final String PROGRESS_PATH = "game_progress.txt";

    /**
     * Initialize game controller
     */
    public GameController(Controller parent) {
        super(parent);
    }

    /**
     * Set the home pane
     */
    public void setHomePane() {
        HomePane root = new HomePane(getController());

        getController().setUI(root);

        getController().getPrimaryStage().setScene(new Scene(root, 800, 600));
    }

    /**
     * Set the select pane
     */
    public void setSelectPane() {
        SelectPane root = new SelectPane(getController());

        getController().setUI(root);

        getController().getPrimaryStage().setScene(new Scene(root, 800, 600));
    }

    /**
     * Start the game
     */
    public void startGame(ArrayList<Pokemua> selectedPokemuas) {
        getGameInstance().startGame(selectedPokemuas);

        getController().getGameController().setFightPane();

        getController().getFightController().startTicker();
    }

    /**
     * Set the fight pane
     */
    public void setFightPane() {
        FightPane root = new FightPane(getController());

        getController().setUI(root);

        Scene scene = new Scene(root, 800, 600);
        root.postSetScene();

        getController().getPrimaryStage().setScene(scene);
    }

    /**
     * Set the fight win pane
     */
    public void setFightWinPane(FightEndEvent event) {
        FightWinPane root = new FightWinPane(getController(), event);

        getController().setUI(root);

        getController().getPrimaryStage().setScene(new Scene(root, 800, 600));
    }

    /**
     * Set the fight lose pane
     */
    public void setFightLosePane() {
        FightLosePane root = new FightLosePane(getController());

        getController().setUI(root);

        getController().getPrimaryStage().setScene(new Scene(root, 800, 600));
    }

    /**
     * Set the first pokemua pane
     */
    public void setFirstPokemuaPane() {
        FirstPokemuaPane root = new FirstPokemuaPane(getController());

        getController().setUI(root);

        getController().getPrimaryStage().setScene(new Scene(root, 800, 600));
    }

    /**
     * Save game progress
     */
    public void saveGameProgress() throws IOException, IllegalAccessException {

        Serializer s = new Serializer();
        s.serialize(getGameInstance().getPlayer());

        File f = new File(PROGRESS_PATH);
        f.createNewFile();

        String serialized = s.build();

        FileWriter writer = new FileWriter(f);
        writer.write(serialized);
        writer.close();
    }
}
