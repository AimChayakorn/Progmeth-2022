package component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.pokemua.Pokemua;

public class PokemuaCard extends GridPane {
    private String imageURL;
    private Image image;
    private Pokemua pokemua;

    public PokemuaCard(Pokemua pokemua) {
        this.setAlignment(Pos.CENTER);
        this.pokemua = pokemua;
        Text nameText = new Text(String.format("%s", pokemua.getName()));
        nameText.setFont(new Font(20));
        Text hpText = new Text(String.format("HP: %d/%d", pokemua.getHp(), pokemua.getMaxHp()));
        if (pokemua.getHp() < 0) {
            hpText.setText("Dead");
        }
        Text levelText = new Text(String.format("Level: %d", pokemua.getLevel()));
        ImageView imageView = new ImageView(new Image(pokemua.getImageURI()));
        imageView.setFitHeight(56);
        imageView.setFitWidth(56);
        GridPane.setRowSpan(imageView, 2);
        this.setPadding(new Insets(10, 25, 10, 25));
        this.setHgap(5);
        setWidth(250);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, new BorderWidths(1))));
        this.add(imageView, 0, 0);
        this.add(nameText, 1, 0);
        this.add(hpText, 1, 1);
        this.add(levelText, 1, 2);
    }

    public Pokemua getPokemua() {
        return pokemua;
    }
}
