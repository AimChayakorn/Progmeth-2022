package pane;

import controllers.Controller;
import event.player.LevelUpEvent;
import event.player.PlayerEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class HomePane extends StackPane implements UIEventListener<PlayerEvent> {
    private Controller controller;
    private VBox layout;
    private Text levelText;
    private Text headText;
    private Button fightBtn;
    private AudioClip sound;

    public HomePane(Controller controller) {
        super();
        this.controller = controller;

        headText = new Text("Pokemua");
        headText.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        sound = new AudioClip("file:asset/soundeff.mp3");
        layout = new VBox();

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(80);

        Image image = new Image("file:asset/home.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(160);

        levelText = new Text();
        String newLevel = String.format("Player Level: %d", controller.getPlayerController().getPlayerLevel());

        levelText.setText(newLevel);
        levelText.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

        fightBtn = new Button("Fight!");
        fightBtn.setPrefWidth(100);
        fightBtn.setPrefHeight(60);

        fightBtn.setOnMouseClicked(e -> {
            sound.play();
            controller.getGameController().setSelectPane();
        });

        layout.getChildren().addAll(headText, imageView, levelText, fightBtn);

        this.getChildren().add(layout);
    }

    private void setNewLevel(int newLevel) {
        String displayText = String.format("Player Level: %d", newLevel);

        levelText.setText(displayText);
    }

    public void handleEvent(PlayerEvent payload) {
        if (payload instanceof LevelUpEvent) {
            handleLevelUpEvent((LevelUpEvent) payload);
        }
    }

    private void handleLevelUpEvent(LevelUpEvent event) {
        int newLevel = event.getNewLevel();
        setNewLevel(newLevel);
    }
}
