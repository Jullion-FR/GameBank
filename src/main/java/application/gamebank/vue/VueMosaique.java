package application.gamebank.vue;

import application.gamebank.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HexFormat;

public class VueMosaique extends Vue{
    public static final int DEFAULT_MAX_GRID_LENGTH = 4;
    private final int maxSize = 175;
    public VueMosaique() {
        createGridElement();
    }
    @Override
    protected  void  createGridElement() {
        maxGridLength = DEFAULT_MAX_GRID_LENGTH;
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("images/mosaique.png")));
        formatGameImageView(img);

        gridElement = new GridElement(new VBox());
        gridElement.addGameImageHolder(img);
        gridElement.addNameHolder(new Label("GamePlaceHolder"));

    }
    @Override
    public void formatGameImageView(ImageView imageView){
        imageView.setFitHeight(maxSize);
        imageView.setFitWidth(maxSize);
    }

    @Override
     void formatRoot(Pane root) {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-alignment: center;-fx-background-color:  #3F5A90;-fx-background-radius:10 ;");
    }

    @Override
    void formatNameLabel(Label nameLabel) {
        nameLabel.setMaxWidth(maxSize);
        nameLabel.setStyle("-fx-alignment: center; -fx-text-overrun: ellipsis; -fx-text-fill:white;");
    }

}
