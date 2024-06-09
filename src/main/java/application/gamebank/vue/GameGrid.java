package application.gamebank.vue;

import application.gamebank.Main;
import application.gamebank.controllers.JeuController;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameGrid {
//    public static Vue VUE_MOSAIQUE = new VueMosaique();
//    public static Vue VUE_LISTE = new VueListe();
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
            Image img = game.getImage();

            //Game name
            Label nameHolder = element.getNameHolder();
            nameHolder.setText(name);

            //Game image
            ImageView imageHolder = element.getGameImageHolder();
            imageHolder.setImage(img);


            Pane box = element.getRoot();
            box.setId(String.valueOf(k));

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
}
