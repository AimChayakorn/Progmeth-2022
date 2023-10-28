package pane;

import controllers.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class FightLosePane extends StackPane {
    private VBox layout;
    private Controller controller;
    private Image image;
    private ImageView imageView;
    private AudioClip sound;

    public FightLosePane(Controller controller) {
        this.controller = controller;
        sound = new AudioClip("file:asset/soundeff.mp3");

        layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(50);
        layout.setPadding(new Insets(40));

        image = new Image("file:asset/satoshicry.jpeg");
        imageView = new ImageView(image);
        imageView.setFitHeight(180);
        imageView.setFitWidth(240);

        Text t = new Text("Game over...");
        t.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Button toHome = new Button("Return to home");
        toHome.setPrefHeight(60);
        toHome.setPrefWidth(120);
        toHome.setOnMouseClicked(e -> {
            sound.play();
            controller.getGameController().setHomePane();
        });

        layout.getChildren().addAll(t, imageView, toHome);

        getChildren().add(layout);
    }
}
