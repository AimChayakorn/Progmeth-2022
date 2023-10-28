package pane;

import component.PokemuaCard;
import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.pokemua.Pokemua;

import java.util.ArrayList;

public class SelectPane extends StackPane {
    private Controller controller;

    private GridPane selectPokemuaLayout;
    private VBox layout;
    private Text headText;
    private Button confirmButton;
    private ArrayList<Pokemua> waitToConfirm;
    private AudioClip sound;

    public SelectPane(Controller controller) {
        super();
        this.controller = controller;
        waitToConfirm = new ArrayList<Pokemua>();

        headText = new Text("Please select 1-3 Pokemuas");
        headText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        confirmButton = new Button("Confirm!");

        HBox button = new HBox();
        button.setPadding(new Insets(30));
        button.getChildren().add(confirmButton);
        button.setAlignment(Pos.BOTTOM_RIGHT);

        sound = new AudioClip("file:asset/soundeff.mp3");
        int i = 0;

        layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(40);
        layout.setPadding(new Insets(30));

        ScrollPane scroll = new ScrollPane();
        selectPokemuaLayout = new GridPane();
        selectPokemuaLayout.setAlignment(Pos.TOP_CENTER);
        selectPokemuaLayout.setPadding(new Insets(5));
        for (Pokemua pokemua : controller.getPlayerController().getPokemuas()) {
            PokemuaCard pokemuaCard = new PokemuaCard(pokemua);
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!waitToConfirm.contains(pokemua) && waitToConfirm.size() < 3) {
                        pokemuaCard.setBackground(Background.fill(Color.GAINSBORO)); //highlight
                        waitToConfirm.add(pokemua);
                    } else if (waitToConfirm.contains(pokemua)) {
                        pokemuaCard.setBackground(Background.EMPTY); //back to normal
                        waitToConfirm.remove(pokemua);
                    }
                    sound.play();
                }
            };
            pokemuaCard.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            selectPokemuaLayout.add(pokemuaCard, i / 3, (i % 3));
            i += 1;
        }

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sound.play();
                if (waitToConfirm.size() >= 1 && waitToConfirm.size() <= 3) {
                    controller.getGameController().startGame(waitToConfirm);
                }
            }
        });
        scroll.setContent(selectPokemuaLayout);
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        layout.getChildren().addAll(headText, scroll, button);
        this.getChildren().add(layout);

    }

}
