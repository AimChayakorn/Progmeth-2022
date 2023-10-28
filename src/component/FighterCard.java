package component;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.fighter.EngagedPlayer;
import logic.fighter.Fighter;
import logic.fighter.WildPokemua;

public class FighterCard extends VBox {

    private Fighter fighter;
    private Text nameText;
    private Text hpText;
    private Text levelText;
    private ProgressBar progressBar;
    private String imageURI;
    private ImageView imageView;
    private Image image;
    private FadeTransition clawFade;
    private Image clawImage;
    private ImageView playerClaw;

    public FighterCard(Fighter fighter) {
        this.fighter = fighter;

        String displayName = fighter.getCurrentPokemua().getName();
        if (fighter instanceof WildPokemua) {
            displayName = "Wild " + displayName;
        }

        clawImage = new Image("file:asset/claw2.png");
        playerClaw = new ImageView(clawImage);
        playerClaw.setFitWidth(50);
        playerClaw.setFitHeight(50);
        playerClaw.setOpacity(0);
        playerClaw.setStyle("-fx-background-color: transparent;");
        clawFade = new FadeTransition(Duration.seconds(0.75), playerClaw);
        clawFade.setFromValue(1);
        clawFade.setToValue(0);
        clawFade.setCycleCount(1);
        nameText = new Text(displayName);
        nameText.setFont(new Font(20));
        hpText = new Text(String.format("HP: %d/%d", fighter.getCurrentPokemua().getHp(), fighter.getCurrentPokemua().getMaxHp()));
        levelText = new Text(String.format("level: %d", fighter.getCurrentPokemua().getLevel()));
        progressBar = new ProgressBar(fighter.getCurrentPokemua().getHp() / fighter.getCurrentPokemua().getMaxHp());
        if (fighter instanceof EngagedPlayer) {
            progressBar.setStyle("-fx-accent: green");
        } else {
            progressBar.setStyle("-fx-accent: #ed3c2f");
        }
        imageURI = fighter.getCurrentPokemua().getImageURI();
        image = new Image(imageURI);
        imageView = new ImageView(image);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, new BorderWidths(1))));
        this.setPadding(new Insets(25));
        setAlignment(Pos.CENTER);
        this.getChildren().addAll(imageView, nameText, hpText, progressBar, levelText);
        this.getChildren().add(playerClaw);
        VBox.setMargin(playerClaw, new Insets(-60, 0, 0, 0));
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void update() {
        imageURI = fighter.getCurrentPokemua().getImageURI();
        image = new Image(imageURI);
        imageView.setImage(image);
        levelText.setText(String.format("level: %d", fighter.getCurrentPokemua().getLevel()));
        String displayName = fighter.getCurrentPokemua().getName();
        if (fighter instanceof WildPokemua) {
            displayName = "Wild " + displayName;
        }
        nameText.setText(displayName);
        hpText.setText(String.format("HP: %d/%d", fighter.getCurrentPokemua().getHp(), fighter.getCurrentPokemua().getMaxHp()));
        progressBar.setProgress(1.0f * fighter.getCurrentPokemua().getHp() / fighter.getCurrentPokemua().getMaxHp());
    }
    public ImageView getImageView() {
        return playerClaw;
    }
    public Image getImage() {
        return clawImage;
    }
    public FadeTransition getClawFade() {
        return clawFade;
    }
    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

}
