package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.games.Game;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JeuController {
    @FXML
    private AnchorPane addGamePane;

    @FXML
    private AnchorPane addTagPane;

    @FXML
    private AnchorPane backPane;

    @FXML
    private Label description;

    @FXML
    private AnchorPane dropGamePane;

    @FXML
    private AnchorPane dropTagPane;

    @FXML
    private ImageView image;

    @FXML
    private Label nom;

    private Scene lastScene;
    private Game game;
    private APIManager apiManager;


    public void chargerJeu(Game gameToLoad) {
        this.game = gameToLoad;
        System.out.println("Chargement de : "+game);
        nom.setText(this.game.getName());
        Image imageAvecUrl = new Image(game.getImageURL());
        image.setImage(imageAvecUrl);
       // description.setText(game.getDescription);
    }

    public void setLastScene(Scene scene) {
        this.lastScene = scene;
    }

    @FXML
    void addGameToLibrary(MouseEvent event) {
        Main.accueilController.getGames().addGame(game);
        Scene thisScene = ((Pane) event.getSource()).getScene();
        ((Stage) thisScene.getWindow()).setScene(Main.accueilController.getScene());
        Main.accueilController.fillView();
    }

    @FXML
    void addTagToGame(MouseEvent event) {

    }

    @FXML
    void backToLastWindow(MouseEvent event) {
        Scene thisScene = ((Pane) event.getSource()).getScene();
        ((Stage) thisScene.getWindow()).setScene(lastScene);
    }

    @FXML
    void removeGameFromLibrary(MouseEvent event) {

    }

    @FXML
    void removeTagFromGame(MouseEvent event){

    }


    void activateAddGamePane(){
        addGamePane.setDisable(false);
    }
    void activateAddTagPane(){
        addTagPane.setDisable(false);
    }
    void activateDropGamePane(){
        dropGamePane.setDisable(false);
    }
    void activateDropTagPane(){
        dropTagPane.setDisable(false);
    }

    //Utilité des méthodes de deactivation à voir (ils commencent déjà desactivé)
    void desactivateAddGamePane(){
        addGamePane.setDisable(true);
    }
    void desactivateAddTagPane(){
        addTagPane.setDisable(true);
    }
    void desactivateDropGamePane(){
        dropGamePane.setDisable(true);
    }
    void desactivateDropTagPane(){
        dropTagPane.setDisable(true);
    }
}
