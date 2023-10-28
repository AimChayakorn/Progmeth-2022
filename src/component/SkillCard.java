package component;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.skill.Skill;

public class SkillCard extends VBox {

    private Skill skill;
    private Text displayName;
    private ProgressBar progressBar;

    public SkillCard(Skill skill, int currentTick) {

        this.skill = skill;

        this.setPadding(new Insets(25));

        displayName = new Text(skill.getSkillName());
        progressBar = new ProgressBar(getProgress(currentTick));

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.getChildren().addAll(displayName, progressBar);
    }

    public void update(int currentTick) {
        progressBar.setProgress(getProgress(currentTick));
    }

    public Skill getSkill() {
        return skill;
    }

    public void select() {
        setBackground(Background.fill(Color.GAINSBORO));
    }

    public void unSelect() {
        setBackground(Background.EMPTY);
    }

    private double getProgress(int currentTick) {
        return Math.min(1.0f * (currentTick - skill.getLastUsed()) / skill.getCoolDown(), 1.0);
    }
}

