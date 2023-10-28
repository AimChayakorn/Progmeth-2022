package pane;

import component.Callback;
import component.PokemuaCard;
import component.PokemuaStatChangeCard;
import controllers.Controller;
import event.fight.FightEndEvent;
import event.pokemua.PokemuaEvent;
import event.pokemua.PokemuaSkillChangeEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.pokemua.Pokemua;
import logic.skill.Skill;

public class FightWinPane extends StackPane implements UIEventListener<PokemuaEvent> {

    private VBox layout;
    private Controller controller;
    private VBox headLayout;
    private HBox newStatLayout;
    private GridPane pokemuaStatLayout;
    private VBox newPokemuaLayout;
    private AudioClip sound;

    public FightWinPane(Controller controller, FightEndEvent event) {
        this.controller = controller;

        String image_path = ClassLoader.getSystemResource("soundeff.mp3").toString();
        sound = new AudioClip(image_path);
        layout = new VBox();

        pokemuaStatLayout = new GridPane();
        pokemuaStatLayout.setAlignment(Pos.CENTER);
        pokemuaStatLayout.setHgap(20);
        pokemuaStatLayout.setVgap(20);
        pokemuaStatLayout.setPadding(new Insets(20));
        newStatLayout = new HBox();
        newStatLayout.setSpacing(30);
        newStatLayout.setPadding(new Insets(20));

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Text headText = new Text("Congratulations!");
        headText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        headLayout = new VBox();
        headLayout.setAlignment(Pos.CENTER);
        headLayout.setSpacing(20);
        headLayout.getChildren().add(headText);

        newPokemuaLayout = new VBox();
        newPokemuaLayout.setSpacing(30);
        newPokemuaLayout.setAlignment(Pos.CENTER_RIGHT);

        if (event.playerLevelUp()) {
            VBox playerLayout = new VBox();
            playerLayout.setSpacing(10);
            playerLayout.setAlignment(Pos.CENTER);
            playerLayout.getChildren().addAll(
                    new Text("Player level up!"),
                    new Text(String.format("%d -> %d", event.getOldPlayerLevel(), event.getNewPlayerLevel()))
            );
            headLayout.getChildren().add(playerLayout);
        }


        int i = 0;
        for (FightEndEvent.PokemuaLevelChange change : event.getPokemuaLevelChanges()) {
            pokemuaStatLayout.add(new PokemuaStatChangeCard(change, getUnlearnCallback(change)), i / 2, i% 2);
            i = i + 1;
        }

        if (event.getNewPokemuas().size() > 0) {
            HBox textNewPokemua = new HBox();
            textNewPokemua.setAlignment(Pos.CENTER);
            Text newPokemua = new Text("New Pokemua");
            textNewPokemua.getChildren().add(newPokemua);
            newPokemuaLayout.getChildren().add(textNewPokemua);
            for (Pokemua pokemua : event.getNewPokemuas()) {
                PokemuaCard pokemuaCard = new PokemuaCard(pokemua);
                pokemuaCard.setAlignment(Pos.CENTER);
                newPokemuaLayout.getChildren().add(pokemuaCard);
            }
        }
        newStatLayout.getChildren().addAll(pokemuaStatLayout, newPokemuaLayout);

        Button toHome = new Button("return to home");
        HBox button = new HBox();
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().add(toHome);
        toHome.setOnMouseClicked(e -> {
            sound.play();
            try {
                controller.getGameController().saveGameProgress();
            } catch (Exception exception) {
                System.err.println("Unable to save game progress");
                exception.printStackTrace();
            }
            controller.getGameController().setHomePane();
        });

        layout.getChildren().addAll(headLayout, newStatLayout, button);

        getChildren().addAll(layout);
    }

    private void _handleEvent(PokemuaSkillChangeEvent event) {
        for (Node node : pokemuaStatLayout.getChildren()) {
            if (node instanceof PokemuaStatChangeCard) {
                PokemuaStatChangeCard card = (PokemuaStatChangeCard) node;

                if (card.getPokemua() == event.getPokemua()) {
                    card.renderPendingSkills();
                }
            }
        }
    }

    @Override
    public void handleEvent(PokemuaEvent payload) {
        switch (payload.getType()) {
            case POKEMUA_SKILL_CHANGE -> {
                _handleEvent((PokemuaSkillChangeEvent) payload);
            }
        }
    }

    public Callback<MouseEvent, Skill> getUnlearnCallback(FightEndEvent.PokemuaLevelChange change) {
        return new Callback<MouseEvent, Skill>() {
            @Override
            public void call(MouseEvent event, Skill data) {
                Pokemua pokemua = change.getPokemua();

                controller.getPokemuaController().unlearnSkill(pokemua, data);
            }
        };
    }
}
