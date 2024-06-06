package application.gamebank.vue;

import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameGrid {
//    public static Vue VUE_MOSAIQUE = new VueMosaique();
//    public static Vue VUE_LISTE = new VueListe();
    private  Vue vue;
    private final GridPane grid;
    public GameGrid() {
        grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10,10,10,60));
        grid.setHgap(10); // Espacement horizontal entre les cellules
        grid.setVgap(10); // Espacement vertical entre les cellules
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setVue(Vue vue){
        this.vue = vue;
    }

    public void fill(MyGames games, int maxGridWidth) {
        //grid.getChildren().clear(); // Clear existing elements

        int i = 0, j = 0;
        for (Game game : games.getAllGames()) {
            GridElement element = vue.getGridElement().clone();

            String name = game.getName();
            Image img = game.getImage();

            //Game name
            Label nameHolder = element.getNameHolder();
            nameHolder.setText(name);
            vue.formatNameLabel(nameHolder);

            //Game image
            ImageView logoHolder = element.getGameImageHolder();
            logoHolder.setImage(img);
            vue.formatGameImageView(logoHolder);

            //Other Specific  Formating
            Pane box = element.getRoot();
            vue.formatRoot(box);

            grid.add(box, i, j);
            i++;
            if (i == maxGridWidth) {
                i = 0;
                j++;
            }
        }
    }

}
