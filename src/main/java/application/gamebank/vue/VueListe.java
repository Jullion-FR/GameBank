package application.gamebank.vue;

import application.gamebank.Main;
import application.gamebank.games.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueListe  extends Vue{
    public static final int DEFAULT_MAX_GRID_LENGTH = 1;

    public VueListe() {
        createGridElement();
    }
    @Override
    void createGridElement() {
        maxGridLength = DEFAULT_MAX_GRID_LENGTH;
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("images/mosaique.png")));
        formatGameImageView(img);

        gridElement = new GridElement(new HBox());
        gridElement.addGameImageHolder(img);
        gridElement.addNameHolder(new Label("GamePlaceHolder"));
    }

    @Override
    void formatGameImageView(ImageView imageView) {
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
    }

    @Override
    void formatRoot(Pane root) {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-alignment: center;-fx-background-color: #9A5ACCBF;-fx-background-radius:10 ;");
    }

    @Override
    void formatNameLabel(Label nameLabel) {

    }
}
