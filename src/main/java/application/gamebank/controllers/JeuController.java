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

    public JeuController() {
        super();
        game = new Game();
    }
    public void ChargerJeu() {
        nom.setText(game.getName());
        Image imageavecUrl = new Image(game.getImageURL());
        image.setImage(imageavecUrl);
       /// description.setText(game.getDescription);
    }
}
