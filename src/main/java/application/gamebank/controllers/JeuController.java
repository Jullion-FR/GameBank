package application.gamebank.controllers;

import application.gamebank.api.APIManager;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JeuController {
    @FXML
    private Label description;

    @FXML
    private ImageView image;

    @FXML
    private Label nom;

    private Game game;
    private APIManager apiManager;

    public void chargerJeu(Game gameToLoad) {
        this.game = gameToLoad;
        System.out.println("Chargement de : "+game);
        nom.setText(this.game.getName());
        Image imageAvecUrl = new Image(game.getImageURL());
        image.setImage(imageAvecUrl);

       /// description.setText(game.getDescription);
    }
}
