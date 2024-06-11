package application.gamebank.vue;

import application.gamebank.Main;
import application.gamebank.games.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VueListe extends Vue {
    public static final int DEFAULT_MAX_GRID_LENGTH = 1;

    public VueListe() {
        createGridElement();
    }

    @Override
    void createGridElement() {
        maxGridLength = DEFAULT_MAX_GRID_LENGTH;
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("images/mosaique.png")));
        Label name = new Label("GamePlaceHolder");

        gridElement = new GridElement(new HBox());

        gridElement.addGameImageHolder(img);
        gridElement.addNameHolder(name);
        gridElement.setHasOtherInfo(true);
    }

    @Override
    void formatGameImageView(ImageView imageView) {
        int maxSize = 100;
        imageView.setFitHeight(maxSize);
        imageView.setFitWidth(maxSize);
    }

    @Override
    void formatRoot(Pane root) {
        root.setMinWidth(maxViewWidth);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color:  #3F5A90;-fx-background-radius:10 ;");
    }

    @Override
    void formatNameLabel(Label nameLabel) {
        nameLabel.setPadding(new Insets(0, 0, 5, 10));
        nameLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold");
    }



}
