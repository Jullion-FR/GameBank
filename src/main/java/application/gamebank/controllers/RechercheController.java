package application.gamebank.controllers;

import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import application.gamebank.games.MyGames;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import application.gamebank.vue.VueMosaique;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RechercheController {
    private Vue vue = new VueMosaique();
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
        System.out.println("Recherche en cours...");
        try {
            apiManager.setInformations(games, searchedText, 27);
        } catch (GameNotFoundException e) {
            entry.clear();
            //Message rien trouvé
            System.out.println("Rien trouvé");
            return;
        }
        entry.clear();
        gameGrid = new GameGrid();
        //voir pour le centrage spécifique entre l'acceuil et la recherche
        // -> Largeur max, combien demander et padding
        // (ptet mettre dans anchorpane)
        gameGrid.setVue(vue);
        gameGrid.fill(games, 9);
        scrollPane.setContent(gameGrid.getGrid());
        System.out.println("Done.");
    }


}
