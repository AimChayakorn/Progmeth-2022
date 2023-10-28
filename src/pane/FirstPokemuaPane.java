package pane;

import component.PokemuaCard;
import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaLoader;

import java.util.ArrayList;


public class FirstPokemuaPane extends StackPane {
    private Controller controller;
    private VBox layout;
    private HBox selectPokemuaLayout;
    private HBox pokemuaInfoLayout;
    private HBox headLayout;
    private Text headText;
    private Text info;
    private Button confirmButton;
    private ArrayList<Pokemua> pokemuas;
    private Pokemua selectedPokemua;
    private boolean selected;
    private ImageView doctorPic;
    private AudioClip sound;
    private ImageView infoImage;

    public FirstPokemuaPane(Controller controller) {
        super();
        this.controller = controller;
        sound = new AudioClip("file:asset/soundeff.mp3");
        doctorPic = new ImageView(new Image("file:asset/docpic.jpg"));
        doctorPic.setFitWidth(80);
        doctorPic.setFitHeight(80);
        headText = new Text("Please select your first Pokemua!");
        headText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        confirmButton = new Button("Confirm!");
        HBox button = new HBox();
        button.setPadding(new Insets(60));
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().add(confirmButton);
        selectedPokemua = null;
        infoImage = new ImageView();

        pokemuas = new ArrayList<>();
        Pokemua bulbasaur = PokemuaLoader.load(1, 5);
        Pokemua charmander = PokemuaLoader.load(4, 5);
        Pokemua squirtle = PokemuaLoader.load(7, 5);
        pokemuas.add(bulbasaur);
        pokemuas.add(charmander);
        pokemuas.add(squirtle);

        info = new Text("");
        info.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        selected = false;
        layout = new VBox();
        selectPokemuaLayout = new HBox();
        selectPokemuaLayout.setAlignment(Pos.CENTER);
        selectPokemuaLayout.setSpacing(20);
        pokemuaInfoLayout = new HBox();
        pokemuaInfoLayout.setAlignment(Pos.CENTER);
        pokemuaInfoLayout.setPadding(new Insets(30));
        pokemuaInfoLayout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, new CornerRadii(3), new BorderWidths(1))));
        headLayout = new HBox();
        headLayout.setAlignment(Pos.CENTER);
        headLayout.setSpacing(10);

        headLayout.getChildren().addAll(doctorPic, headText);

        layout.setSpacing(40);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        pokemuaInfoLayout.getChildren().addAll(infoImage, info);

        for (Pokemua pokemua : pokemuas) {
            PokemuaCard pokemuaCard = new PokemuaCard(pokemua);
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    sound.play();
                    if (!selected) {
                        pokemuaCard.setBackground(Background.fill(Color.GAINSBORO));
                        selectedPokemua = pokemua;
                        setText(pokemua);
                        selected = true;
                    } else if (selected && selectedPokemua == pokemua) {
                        pokemuaCard.setBackground(Background.EMPTY);
                        selectedPokemua = null;
                        info.setText("");
                        infoImage.setImage(null);
                        selected = false;
                    }
                }
            };
            pokemuaCard.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            selectPokemuaLayout.getChildren().add(pokemuaCard);
        }
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sound.play();
                if (selectedPokemua != null) {
                    controller.getGameInstance().getPlayer().getPokemuas().add(selectedPokemua);
                    controller.getGameController().setHomePane();
                }
            }
        });
        layout.getChildren().addAll(headLayout, pokemuaInfoLayout, selectPokemuaLayout, button);
        this.getChildren().add(layout);
    }

    private void setText(Pokemua pokemua) {
        if (pokemua.getName() == "Bulbasaur") {
            infoImage.setImage(new Image("file:asset/pokemua/0001.png"));
            info.setText("A strange seed was planted on its back at birth. \nThe plant sprouts and grows with the Pokemua.");
        } else if (pokemua.getName() == "Charmander") {
            info.setText("Obviously prefers hot places. When it rains. \nSteam is said to spout from the tip of its tail");
            infoImage.setImage(new Image("file:asset/pokemua/0004.png"));
        } else if (pokemua.getName() == "Squirtle") {
            info.setText("After birth, its back swells and hardens into a shell. \nPowerfully sprays foam from its mouth.");
            infoImage.setImage(new Image("file:asset/pokemua/0007.png"));
        }
    }
}

