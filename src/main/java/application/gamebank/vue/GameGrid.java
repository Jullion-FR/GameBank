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
import javafx.scene.text.Text;

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

            //Other
            try {
                Text otherInformations = (Text) element.getOtherInformations();
                otherInformations.setText(game.getRating()+" / 5\n\n\n"+game.getReleaseDate());
                vue.formatOtherInfo(otherInformations);
            }catch (Exception ignored) {}


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
