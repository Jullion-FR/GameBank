package application.gamebank.vue;

import application.gamebank.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class VueMosaique extends Vue{

    public VueMosaique(){
        createGridElement();
    }
    @Override
    protected  void  createGridElement() {
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("images/mosaique.png")));
        formatGameImageView(img);
        gridElement = new GridElement(new VBox());
        gridElement.setNameHolder(new Label("GamePlaceHolder"));
        gridElement.setLogoHolder(img,0);
    }
    @Override
    public void formatGameImageView(ImageView imgv){
        imgv.setFitHeight(100);
        imgv.setFitWidth(100);
    }

    @Override
    void formatRoot(Pane root) {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-alignment: center;");
        root.setBackground(Background.fill(Color.PURPLE));
    }

    @Override
    void formatNameLabel(Label nameLabel) {
        nameLabel.setMaxWidth(100);
        nameLabel.setStyle("-fx-alignment: center; -fx-text-overrun: ellipsis;");
    }

}
