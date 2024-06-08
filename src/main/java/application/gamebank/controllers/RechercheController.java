package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import application.gamebank.vue.VueListe;
import application.gamebank.vue.VueMosaique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RechercheController {

    private Vue vueActuelle;
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
    private GameGrid gameGrid;

    @FXML
    public void initialize(){
        games = new MyGames();
        apiManager = new APIManager();
        gameGrid = new GameGrid();
        vueActuelle = gameGrid.getVue();
    }
    @FXML
    public void onAction() {
        int maxGridWidth = 5;
        String searchedText = entry.getText();

        if (searchedText.isBlank())
            return;
        System.out.println("Recherche en cours...");
        entry.clear();
        try {
            games = apiManager.setInformations(searchedText, maxGridWidth*4);
        } catch (GameNotFoundException e) {
            scrollPane.setContent(new Label("Aucun résultat"));
            System.out.println("Rien trouvé");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }


        gameGrid.resetGrid();
        gameGrid.fill(games, maxGridWidth);
        gameGrid.applyPadding(10);

        scrollPane.setContent(gameGrid.getGrid());

        for (Node node : gameGrid.getGrid().getChildren()) {
            node.setOnMouseClicked(this::openGameDetails);
        }


        System.out.println("Done.");
    }
    @FXML
    public void switchView(){
        if (vueActuelle instanceof VueMosaique) {
            vueActuelle = VueListe.VUE_LISTE;
        } else {
            vueActuelle = VueMosaique.VUE_MOSAIQUE;
        }
        gameGrid.setVue(vueActuelle);
    }


        public void openGameDetails(MouseEvent event)  {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/jeu.fxml"));
            Scene scene = new Scene(loader.load());
            Game game = games.getAllGames().get(Integer.parseInt(((Node) event.getSource()).getId()));
            ((JeuController) loader.getController()).chargerJeu(game);
            ((Stage) ((VBox) event.getSource()).getScene().getWindow()).setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
