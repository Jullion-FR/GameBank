package application.gamebank.controllers;

import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import application.gamebank.vue.VueMosaique;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.List;

public class RechercheController {

    private Vue vue = new VueMosaique();
    private MyGames games;
    private APIManager apiManager;
    private GameGrid gameGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField entry;

    @FXML
    private ImageView validateImageView;

    @FXML
    private ImageView pictureImageView;

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
        // voir pour le centrage spécifique entre l'acceuil et la recherche
        // -> Largeur max, combien demander et padding
        // (ptet mettre dans anchorpane)
        gameGrid.setVue(vue);
        gameGrid.fill(games, 9);
        scrollPane.setContent(gameGrid.getGrid());
        System.out.println(games.getAllGames());
        System.out.println("Done.");
    }

    /** Renvoie une liste de jeux trouver grâce à la recherche */
    List<Game> recherche(String request) {

        List<Game> game_liste = new LinkedList<>();

        // Si il n'y a pas de recherche, on renvoie une liste vide
        if (request.isBlank()) return game_liste;

        System.out.println("Début de la recherche");
        try {
            apiManager.setInformations(games, request, 27);
        } catch (GameNotFoundException e) {
            System.err.println("Erreur : GameNotFoundException");
        }
        return game_liste;
    }
}