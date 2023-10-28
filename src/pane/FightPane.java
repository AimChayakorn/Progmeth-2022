package pane;

import component.FighterCard;
import component.PokemuaCard;
import component.SkillCard;
import controllers.Controller;
import event.fight.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.Fight;
import action.ChangePokemuaAction;
import action.UseSkillAction;
import logic.fighter.Fighter;
import logic.pokemua.Pokemua;
import logic.skill.Skill;

import java.util.List;

public class FightPane extends StackPane implements UIEventListener<FightEvent> {

    private Controller controller;
    private Fighter player;
    private List<Fighter> fighters;

    private HBox enemyLayout;
    private HBox skillLayout;
    private VBox changePokemuaLayout;
    private SkillCard selectedSkillCard;
    private Button changePokemuaButton;
    private PokemuaCard waitToChange;
    private FighterCard playerCard;
    private AudioClip attackSound;

    private EventHandler<KeyEvent> hotkeyHandler = (e) -> {
        int skillCount = skillLayout.getChildren().size();
        switch (e.getCode()) {
            case Q -> {
                if (skillCount > 0) {
                    interactSkillCard((SkillCard) skillLayout.getChildren().get(0));
                }
            }
            case W -> {
                if (skillCount > 1) {
                    interactSkillCard((SkillCard) skillLayout.getChildren().get(1));
                }
            }
            case E -> {
                if (skillCount > 2) {
                    interactSkillCard((SkillCard) skillLayout.getChildren().get(2));
                }
            }
            case R -> {
                if (skillCount > 3) {
                    interactSkillCard((SkillCard) skillLayout.getChildren().get(3));
                }
            }
            case DIGIT1 -> {
                if (fighters.size() > 1) {
                    interactFighterCard((FighterCard) enemyLayout.getChildren().get(0));
                }
            }
            case DIGIT2 -> {
                if (fighters.size() > 2) {
                    interactFighterCard((FighterCard) enemyLayout.getChildren().get(1));
                }
            }
            case DIGIT3 -> {
                if (fighters.size() > 3) {
                    interactFighterCard((FighterCard) enemyLayout.getChildren().get(2));
                }
            }
            case DIGIT4 -> {
                if (fighters.size() > 4) {
                    interactFighterCard((FighterCard) enemyLayout.getChildren().get(3));
                }
            }
            case X -> {
                if (changePokemuaLayout.getChildren().size() > 0) {
                    interactChangePokemuaCard((PokemuaCard) changePokemuaLayout.getChildren().get(0));
                }
            }
            case C -> {
                if (changePokemuaLayout.getChildren().size() > 1) {
                    interactChangePokemuaCard((PokemuaCard) changePokemuaLayout.getChildren().get(1));
                }
            }
        }
    };

    public FightPane(Controller controller) {
        super();

        this.controller = controller;

        this.setPadding(new Insets(50));

        HBox paneLayout = new HBox();

        Fight fight = controller.getFightController().getFight();

        attackSound = new AudioClip("file:asset/attackeff.wav");

        player = fight.getEngagedPlayer();
        playerCard = new FighterCard(player);
        fighters = fight.getFighters();

        enemyLayout = new HBox();
        enemyLayout.setAlignment(Pos.CENTER);

        fighters.stream().forEach(fighter -> {
            if (fighter != player) {
                FighterCard fighterCard = new FighterCard(fighter);
                fighterCard.setOnMouseClicked(event -> {
//                    if (selectedSkillCard != null) {
//                        fighterCard.getImageView().setVisible(true);
//                        fighterCard.getClawFade().play();
//                    }
                    interactFighterCard(fighterCard);
                });
                enemyLayout.getChildren().add(fighterCard);
            }
        });

        skillLayout = new HBox();
        skillLayout.setPadding(new Insets(10));
        skillLayout.setSpacing(5);
        skillLayout.setAlignment(Pos.CENTER);
        skillLayout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        updateSkill();

        VBox fightLayout = new VBox();
        playerCard.setAlignment(Pos.CENTER);
        fightLayout.getChildren().addAll(enemyLayout, playerCard, skillLayout);

        changePokemuaLayout = new VBox();
        changePokemuaLayout.setAlignment(Pos.CENTER);
        changePokemuaLayout.setSpacing(30);
        changePokemuaLayout.setPadding(new Insets(20));
        updateChangePokemua();

        paneLayout.getChildren().addAll(fightLayout);

        if (changePokemuaLayout.getChildren().size() > 0) {
            paneLayout.getChildren().add(changePokemuaLayout);
        }
        paneLayout.setHgrow(fightLayout, Priority.ALWAYS);

        this.getChildren().add(paneLayout);
    }

    public void postSetScene() {
        getScene().addEventHandler(KeyEvent.KEY_PRESSED, hotkeyHandler);
    }

    private void interactFighterCard(FighterCard fighterCard) {
        if (selectedSkillCard == null) {
            return;
        }
        Skill skill = selectedSkillCard.getSkill();
        controller.getFightController().act(new UseSkillAction(player, fighterCard.getFighter(), skill));
        selectedSkillCard.unSelect();
        selectedSkillCard = null;

        fighterCard.getImageView().setOpacity(100);
        fighterCard.getClawFade().play();
        fighterCard.getImageView().setOpacity(0);
        attackSound.play();
    }

    private void interactSkillCard(SkillCard skillCard) {
        int tick = controller.getFightController().getTick();
        if (!skillCard.getSkill().isAvailable(tick)) {
            return;
        }
        if (selectedSkillCard != skillCard) {
            if (selectedSkillCard != null) {
                selectedSkillCard.unSelect();
            }
            selectedSkillCard = skillCard;
            selectedSkillCard.select();
        } else {
            selectedSkillCard.unSelect();
            selectedSkillCard = null;
        }
    }

    private void interactChangePokemuaCard(PokemuaCard pokemuaCard) {
        Pokemua pokemua = pokemuaCard.getPokemua();
        Fighter player = controller.getFightController().getFight().getEngagedPlayer();
        if (waitToChange == pokemuaCard) {
            if (!pokemua.isDead()) {
                controller.getFightController().act(new ChangePokemuaAction(player, pokemua));
            }
            pokemuaCard.setBackground(Background.EMPTY);
            waitToChange = null;
            selectedSkillCard = null;
        } else {
            if (waitToChange != null) {
                waitToChange.setBackground(Background.EMPTY);
            }
            waitToChange = pokemuaCard;
            pokemuaCard.setBackground(Background.fill(Color.GAINSBORO));
        }
    }

    private void updateFighter(Fighter newFighter) {
        if (newFighter == player) {
            playerCard.update();
        } else {
            for (Node node : enemyLayout.getChildren()) {
                FighterCard card = (FighterCard) node;
                if (card.getFighter() == newFighter) {
                    card.update();
                }
            }
        }
    }

    private void updateTick(int currentTick) {
        skillLayout.getChildren().forEach(node -> {
            SkillCard card = (SkillCard) node;
            card.update(currentTick);
        });
    }

    private void updateChangePokemua() {
        player.getAllPokemuas().forEach(pokemua -> {
            if (pokemua != player.getCurrentPokemua()) {
                PokemuaCard pokemuaCard = new PokemuaCard(pokemua);
                pokemuaCard.setAlignment(Pos.CENTER_RIGHT);
                pokemuaCard.setPrefWidth(60);
                pokemuaCard.setPrefHeight(60);
                EventHandler<MouseEvent> eventHandler = mouseEvent -> {
                    interactChangePokemuaCard(pokemuaCard);
                };
                pokemuaCard.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
                changePokemuaLayout.getChildren().add(pokemuaCard);
            }
        });
    }

    private void updateSkill() {
        player.getCurrentPokemua().getSkills().forEach(e -> {
            SkillCard skillCard = new SkillCard(e, controller.getFightController().getTick());
            skillCard.setOnMouseClicked((event) -> {
                interactSkillCard(skillCard);
            });

            skillLayout.getChildren().add(skillCard);
        });
    }

    private void _handleEvent(ActionStartEvent event) {
        updateTick(controller.getFightController().getTick());
        if(event.getTarget() == player) {
            playerCard.getImageView().setOpacity(0);
            playerCard.getImageView().setOpacity(100);
            playerCard.getClawFade().play();
            attackSound.play();
        }
        else if(event.getAttacker() != player && event.getTarget() != player) {
            int idx = 0;
            for(Node f : enemyLayout.getChildren()) {
                if(event.getTarget() == ((FighterCard) f).getFighter()) {
                    idx = enemyLayout.getChildren().indexOf(f);
                    break;
                }
            }
            FighterCard card = (FighterCard) enemyLayout.getChildren().get(idx);
            card.getImageView().setOpacity(100);
            card.getClawFade().play();
            card.getImageView().setOpacity(0);
            attackSound.play();
        }
    }

    private void _handleEvent(ActionEndEvent event) {
        updateTick(controller.getFightController().getTick());
        updateFighter(event.getTarget());
    }

    private void _handleEvent(TickEvent event) {
        updateTick(event.getCurrentTick());
    }

    private void _handleEvent(ChangePokemuaEvent event) {
        updateFighter(event.getFighter());
        playerCard.update();
        changePokemuaLayout.getChildren().clear();
        updateChangePokemua();
        skillLayout.getChildren().clear();
        updateSkill();
    }

    private void _handleEvent(FighterDeadEvent event) {

        if (player == event.getFighter()) {
            attackSound.play();
            controller.getFightController().endFight(false);
            controller.getGameController().setFightLosePane();
        }

        fighters = controller.getFightController().getFight().getFighters();

        enemyLayout.getChildren().clear();

        fighters.stream().forEach(fighter -> {
            if (fighter != player) {
                FighterCard fighterCard = new FighterCard(fighter);
                fighterCard.setOnMouseClicked(e -> {
                    if (selectedSkillCard == null) {
                        return;
                    }
                    Skill skill = selectedSkillCard.getSkill();
                    controller.getFightController().act(new UseSkillAction(player, fighter, skill));
                    selectedSkillCard.unSelect();
                    selectedSkillCard = null;
                });
                enemyLayout.getChildren().add(fighterCard);
            }
        });
    }

    private void _handleEvent(FightEndEvent event) {
        controller.getGameController().setFightWinPane(event);
    }

    public void handleEvent(FightEvent payload) {
        switch (payload.getType()) {
            case TICK -> {
                _handleEvent((TickEvent) payload);
            }
            case ACTION_START -> {
                _handleEvent((ActionStartEvent) payload);
            }
            case ACTION_END -> {
                _handleEvent((ActionEndEvent) payload);
            }
            case CHANGE_POKEMUA -> {
                _handleEvent((ChangePokemuaEvent) payload);
            }
            case FIGHTER_DEAD_EVENT -> {
                _handleEvent((FighterDeadEvent) payload);
            }
            case FIGHT_END -> {
                _handleEvent((FightEndEvent) payload);
            }
        }
    }
}
