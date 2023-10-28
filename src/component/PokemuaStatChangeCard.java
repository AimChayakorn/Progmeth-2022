package component;

import event.fight.FightEndEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaStat;
import logic.skill.Skill;

public class PokemuaStatChangeCard extends VBox {

    private VBox oldSkill;
    private VBox newSkill;
    private HBox currentSkillLayout;
    private HBox pendingSkillLayout;
    private HBox headLayout;
    private Pokemua pokemua;

    private Callback<MouseEvent, Skill> skillCallback;

    public PokemuaStatChangeCard(FightEndEvent.PokemuaLevelChange change, Callback<MouseEvent, Skill> skillCallback) {
        ImageView imageView = new ImageView(new Image(change.getPokemua().getImageURI()));
        imageView.setFitHeight(56);
        imageView.setFitWidth(56);

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, new BorderWidths(1))));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(30));

        this.skillCallback = skillCallback;

        PokemuaStat oldStat = change.getOldStat();
        PokemuaStat newStat = change.getNewStat();

        headLayout = new HBox();
        headLayout.getChildren().add(imageView);

        VBox newStatLayout = new VBox();
        newStatLayout.setSpacing(3);
        newStatLayout.setPadding(new Insets(10));
        newStatLayout.getChildren().addAll(
                new Text(String.format("HP: %d -> %d", oldStat.getMaxHp(), newStat.getMaxHp())),
                new Text(String.format("Attack: %d -> %d", oldStat.getAttack(), newStat.getAttack())),
                new Text(String.format("Defense: %d -> %d", oldStat.getDefense(), newStat.getDefense()))
        );

        System.out.println(change.getNewSkills());
        change.getNewSkills().forEach(skill -> {
            newStatLayout.getChildren().add(new Text(String.format("New skill: %s", skill.getName())));
        });

        headLayout.getChildren().add(newStatLayout);

        setAlignment(Pos.CENTER);
        setSpacing(20);

        currentSkillLayout = new HBox();
        pendingSkillLayout = new HBox();
        pokemua = change.getPokemua();

        currentSkillLayout.setAlignment(Pos.CENTER);
        currentSkillLayout.setSpacing(20);

        pendingSkillLayout.setSpacing(10);
        pendingSkillLayout.setAlignment(Pos.CENTER);

        newSkill = new VBox();
        oldSkill = new VBox();
        oldSkill.setAlignment(Pos.CENTER);
        newSkill.setAlignment(Pos.CENTER);
        oldSkill.setSpacing(20);

        if (pokemua.hasPendingSkill()) {
            renderPendingSkills();
            this.getChildren().addAll(oldSkill, newSkill);
        }

        this.getChildren().add(headLayout);
    }

    public Pokemua getPokemua() {
        return pokemua;
    }

    public void renderPendingSkills() {
        if (!pokemua.hasPendingSkill()) {
            getChildren().removeAll(oldSkill, newSkill);
        } else {
            currentSkillLayout.getChildren().clear();
            pendingSkillLayout.getChildren().clear();
            oldSkill.getChildren().clear();
            newSkill.getChildren().clear();
            Text chooseSkill = new Text("Choose skill to be removed");
            oldSkill.getChildren().add(chooseSkill);
            for (Skill skill : pokemua.getSkills()) {
                Text t = new Text(skill.getSkillName());
                t.setOnMouseClicked(event -> PokemuaStatChangeCard.this.skillCallback.call(event, skill));
                currentSkillLayout.getChildren().add(t);
            }
            pokemua.getPendingSkills().forEach(skill -> {
                Text t = new Text(skill.getSkillName());
                t.setOnMouseClicked(event -> PokemuaStatChangeCard.this.skillCallback.call(event, skill));
                pendingSkillLayout.getChildren().add(t);
            });
            oldSkill.getChildren().add(currentSkillLayout);
            newSkill.getChildren().add(pendingSkillLayout);

        }
    }
}
