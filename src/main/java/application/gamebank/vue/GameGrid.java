package application.gamebank.vue;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import javax.swing.plaf.synth.Region;

public class GameGrid {
    private Vue vue;
    private GridPane grid;
    public GameGrid(Vue defaultView) {
        vue = defaultView;
        resetGrid();
    }
    public void resetGrid() {
        grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
    }
    public void applyPadding(double haut, double droite, double bas, double gauche){
        Insets inset = new Insets(haut,droite,bas,gauche);
        grid.setPadding(inset);
    }
    public void applyPadding(double value){
        applyPadding(value, value, value, value);
    }
    public GridPane getGrid() {
        return grid;
    }

    public void setVue(Vue vue){
        this.vue = vue;
    }


    public void fill(MyGames games) {

        int i = 0, j = 0, k = 0;
        for (Game game : games.getAllGames()) {

            GridElement element = vue.getGridElement().clone();

            String name = game.getName();
            Image img;
            try {
                img = game.getImage();
            }catch (Exception e) {
                img = new Image(String.valueOf(Main.class.getResource("images/mosaique.png")));
            }


            //Game name
            Label nameHolder = element.getNameHolder();
            nameHolder.setText(name);

            //Game image
            ImageView imageHolder = element.getGameImageHolder();
            imageHolder.setImage(img);

            //Root
            Pane box = element.getRoot();
            box.setId(String.valueOf(k));

            // has other info?
            if (element.hasOtherInfo()) {
                VBox mBox = new VBox();
                Label note = new Label(String.format("%-" + 8 + "s", game.getRating()+" / 5"));
                Label date = new Label(game.getReleaseDate());

                VBox.setMargin(note, new Insets(0, 0, 0, 10));
                VBox.setMargin(date, new Insets(0, 0, 0, 10));

                labelFormat(note);
                labelFormat(date);

                mBox.setAlignment(Pos.CENTER_LEFT);
                mBox.getChildren().addAll(nameHolder,note, date);
                element.getRoot().getChildren().add(mBox);
            }


            //Format
            vue.formatAll(box, imageHolder, nameHolder);


            grid.add(box, i, j);

            k++;
            i++;
            if (i == vue.getMaxGridLength()) {
                i = 0;
                j++;
            }
        }
    }

    public Vue getVue() {
        return vue;
    }

    private void labelFormat(Label label) {
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 20px;");
        label.setTextAlignment(TextAlignment.LEFT);
    }
}
