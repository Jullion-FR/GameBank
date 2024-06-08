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
    public static final Vue VUE_MOSAIQUE = new VueMosaique();
    private int maxSize = 200;
    public VueMosaique() {
        createGridElement();
    }
    @Override
    protected  void  createGridElement() {
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("images/mosaique.png")));
        formatGameImageView(img);

        gridElement = new GridElement(new VBox());
        gridElement.addGameImageHolder(img);
        gridElement.addNameHolder(new Label("GamePlaceHolder"));

    }
    @Override
    public void formatGameImageView(ImageView imgv){
        imgv.setFitHeight(maxSize);
        imgv.setFitWidth(maxSize);
    }

    @Override
     void formatRoot(Pane root) {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-alignment: center;-fx-background-color: #9A5ACCBF;-fx-background-radius:10 ;");
    }

    @Override
    void formatNameLabel(Label nameLabel) {
        nameLabel.setMaxWidth(maxSize);
        nameLabel.setStyle("-fx-alignment: center; -fx-text-overrun: ellipsis;");
    }

}
