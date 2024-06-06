package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RechercheController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField entry;
    @FXML
    private ImageView validateImageView;

    @FXML
    private ImageView pictureImageView;

    private MyGames games;
    private APIManager apiManager;

    public RechercheController() {
        super();
        games = new MyGames();
        apiManager = new APIManager();
    }

    @FXML
    public void onAction() {

        String searchedText = entry.getText();

        if (searchedText.isBlank())
            return;

        try {
            apiManager.setInformations(games, searchedText, 20);
        } catch (GameNotFoundException e) {
            entry.clear();
            //Message rien trouvé
            return;
        }
        entry.clear();
        GridPane gridPane = newGrid();
        fillGrid(gridPane);
        scrollPane.setContent(gridPane);
    }

    private GridPane newGrid() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10,10,10,30));
        grid.setHgap(10); // Espacement horizontal entre les cellules
        grid.setVgap(10); // Espacement vertical entre les cellules
        return grid;
    }

    private void fillGrid(GridPane gridPane) {
        int maxGridWidth = 6;
        int i = 0, j = 0;
        for (Game game : games.getAllGames()) {
            gridPane.add(createGridComponent(game), i, j);
            i++;
            if (i == maxGridWidth) {
                i = 0;
                j++;
            }
        }
        centerGridChildrens(gridPane);
    }

    private VBox createGridComponent(Game game) {
        VBox boxInGrid = new VBox();
        boxInGrid.setMaxHeight(100);
        boxInGrid.setMaxWidth(75);
        boxInGrid.setAlignment(Pos.CENTER);
        Image img;
        try {
            img = new Image(game.getImageURL());
            //Test lien image trouvé sinon image par défaut
        } catch (Exception e) {
            img = new Image(String.valueOf(Main.class.getResource("/images/mosaique.png")));
        }
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        boxInGrid.getChildren().add(imageView);
        boxInGrid.getChildren().add(new Label(game.getName()));
        boxInGrid.setPadding(new Insets(10)); // Padding autour de chaque boîte
        return boxInGrid;
    }

    private void centerGridChildrens(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            GridPane.setHalignment(node, HPos.CENTER);
            GridPane.setValignment(node, VPos.CENTER);
        }
    }
}
