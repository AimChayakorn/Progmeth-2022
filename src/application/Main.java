package application;

import controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.Game;
import logic.Player;
import serial.Deserializer;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {

    /**
     * Path to save game progress
     */
    private static final String PROGRESS_PATH = "game_progress.txt";

    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start application
     */
    @Override
    public void start(Stage primaryStage) {
        Game instance = getGame();

        Controller controller = new Controller(instance, primaryStage);

        if (instance.getPlayer().getPokemuas().size() == 0) {
            controller.getGameController().setFirstPokemuaPane();
        } else {
            controller.getGameController().setHomePane();
        }

        primaryStage.show();
    }

    /**
     * Get game
     */
    public Game getGame() {
        File f = new File(PROGRESS_PATH);
        if (!f.exists()) {
            return new Game();
        }

        try {
            Scanner reader = new Scanner(f);
            String fileContent = reader.nextLine();

            Deserializer d = new Deserializer(fileContent);

            Player player = d.deserializeClass(Player.class);

            reader.close();

            return new Game(player);
        } catch (Exception e) {
        }
        return new Game();
    }
}
