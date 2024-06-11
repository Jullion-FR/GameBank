package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class JeuController {

    private MyTags all_tags;

    @FXML
    private AnchorPane addGamePane;

    @FXML
    private AnchorPane addTagPane;

    @FXML
    private AnchorPane backPane;

    @FXML
    private AnchorPane dropGamePane;

    @FXML
    private AnchorPane dropTagPane;

    @FXML
    private ImageView image;

    @FXML
    private Label nom;

    @FXML
    private Label noteLabel;

    @FXML
    private VBox tagSection;

    @FXML
    private Label dateSortieLabel;
    private Game game;
    private Scene lastScene;

    @FXML
    void initialize() {
        all_tags = null;
        addTagPane.setDisable(false);
    }

    /** réccupere tout les tags existant depuis la fenêtre précedante */
    public void setAll_tags(MyTags tags) {
        all_tags = tags;

        updateTagSection();
    }

    /** Met à jour la section affichant les tags du jeux */
    private void updateTagSection() {
        // Suppresion des fils du conteneur
        tagSection.getChildren().clear();

        // Recalcule de la hauteur du conteneur
        int n = game.getAllTags().size(); // Nombre de tag
        double s  = tagSection.getSpacing(); // Espace entre deux tag
        double x = Tag.HEIGHT; // Taille des tags

        tagSection.setPrefHeight(n*x + (n-1)*s);

        // Ajout des tag dans le conteneur
        for (Tag t : all_tags.getAllTags()) {
            tagSection.getChildren().add(t.createTagPane());
        }
    }

    public void chargerJeu(Game gameToLoad) {
        this.game = gameToLoad;
        System.out.println("Chargement de : "+game);
        nom.setText(game.getName());
        image.setImage(game.getImage());
        noteLabel.setText(game.getRating() + " / 5");
        dateSortieLabel.setText(game.getReleaseDate());
    }

    public Game getGame() {
        return game;
    }

    @FXML
    void addGameToLibrary(MouseEvent event) {
        Main.accueilController.getGames().addGame(game);
        backToLastWindow(event);
        Main.accueilController.fillView();
    }

    @FXML
    void addTagToGame(MouseEvent event) {
        System.out.println("clique ?");
    }

    @FXML
    void backToLastWindow(MouseEvent event) {
        Scene thisScene = ((Pane) event.getSource()).getScene();
        Stage stage = (Stage) thisScene.getWindow();
        if(lastScene != null) {
            stage.setScene(lastScene);
        }else {stage.close();}
    }

    void setLastScene(Scene scene) {
        this.lastScene = scene;
    }

    @FXML
    void removeGameFromLibrary(MouseEvent event) {
        Main.accueilController.getGames().removeGame(game);
        backToLastWindow(event);
        Main.accueilController.fillView();
    }

    @FXML
    void removeTagFromGame(MouseEvent event){

    }

    void activateAddGamePane(){
        addGamePane.setDisable(false);
        addGamePane.setOpacity(1);
    }

    void activateAddTagPane(){
        addTagPane.setDisable(false);
    }

    void activateDropGamePane(){
        dropGamePane.setDisable(false);
        dropGamePane.setOpacity(1);
    }

    void activateDropTagPane(){
        dropTagPane.setDisable(false);
    }

    // Utilité des méthodes de deactivation à voir (ils commencent déjà desactivé)
    void desactivateAddGamePane(){
        addGamePane.setDisable(true);
        addGamePane.setOpacity(0.5);
    }

    void desactivateAddTagPane(){
        addTagPane.setDisable(true);
    }

    void desactivateDropGamePane(){
        dropGamePane.setDisable(true);
        dropGamePane.setOpacity(0.5);
    }

    void desactivateDropTagPane(){
        dropTagPane.setDisable(true);
    }

    public Scene getScene() {
        return lastScene;
    }
}
