package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
        addTagPane.setDisable(false);
        this.all_tags = Main.accueilController.getTags();
    }

    /**
     * Met à jour la section affichant les tags du jeux
     */
    private void updateTagSection() {
        // Suppresion des fils du conteneur
        tagSection.getChildren().clear();

        // Recalcule de la hauteur du conteneur
        int n = game.getAllTags().size(); // Nombre de tag
        double s = tagSection.getSpacing(); // Espace entre deux tag
        double x = Tag.HEIGHT; // Taille des tags

        tagSection.setPrefHeight(n * x + (n - 1) * s);

        // Ajout des tag dans le conteneur
        for (Tag t : game.getAllTags()) {
            AnchorPane anchorTag = t.createTagPane();
            anchorTag.setOnMouseClicked(this::delTagFromTagSection);
            tagSection.getChildren().add(anchorTag);
        }
    }

    private void delTagFromTagSection(MouseEvent event) {
        System.out.println(dropTagPane.getId());
        if (dropTagPane.getId().equals("On")) {
            for (Node n : ((AnchorPane) event.getSource()).getChildren()) {
                if (n instanceof Label) {
                    String name = ((Label) n).getText();
                    System.out.println("nom du tag : " + name);

                    Tag t = all_tags.getTagByName(name);
                    System.out.println("Tag trouver ?" + t);

                    game.delTag(t);
                    System.out.println("Tag remove succesfully : " + game.getAllTags());
                }
            }
            dropTagPane.setId("Off");
            if (game.getAllTags().isEmpty()) {
                deactivateDropTagPane();
            }
            updateTagSection();
        }
    }

    public void chargerJeu(Game gameToLoad) {
        this.game = gameToLoad;
        if(!this.game.getAllTags().isEmpty()) {
            activateDropTagPane();
        }
        updateTagSection();
        System.out.println("Chargement de : " + game);
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
        try {
            Stage stage = new Stage();
            FXMLLoader root = new FXMLLoader(Main.class.getResource("fxml/AddTag.fxml"));
            stage.setScene(new Scene(root.load()));
            stage.setTitle("Game Bank - Ajouter un Tag");
            stage.initModality(Modality.WINDOW_MODAL);
            AddTagController controller = root.getController();

            stage.showAndWait();
            game.addTag(controller.getResult());

            activateDropTagPane();
            updateTagSection();


        } catch (IOException e) {
            System.err.println("Une erreur est survenue");
        }
    }

    @FXML
    void backToLastWindow(MouseEvent event) {
        Scene thisScene = ((Pane) event.getSource()).getScene();
        Stage stage = (Stage) thisScene.getWindow();
        if (lastScene != null) {
            stage.setScene(lastScene);
        } else {
            stage.close();
        }
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
    void removeTagFromGame(MouseEvent event) {
        dropTagPane.setId("On");
        System.out.println("TagRemover - ON");
    }

    void activateAddGamePane() {
        addGamePane.setDisable(false);
    }

    void activateAddTagPane() {
        addTagPane.setDisable(false);
        addTagPane.setOpacity(1);
    }

    void activateDropGamePane() {
        dropGamePane.setDisable(false);
    }

    void activateDropTagPane() {
        dropTagPane.setId("Off");
        System.out.println("TagRemover - OFF");
        dropTagPane.setDisable(false);
        dropTagPane.setOpacity(1);
    }

    // Utilité des méthodes de deactivation à voir (ils commencent déjà desactivé)
    void deactivateAddGamePane() {
        addGamePane.setDisable(true);
    }

    void deactivateAddTagPane() {
        addTagPane.setDisable(true);
        addTagPane.setOpacity(0.5);
    }

    void deactivateDropGamePane() {
        dropGamePane.setDisable(true);
    }

    void deactivateDropTagPane() {
        dropTagPane.setDisable(true);
        dropTagPane.setOpacity(0.5);
    }

    public Scene getScene() {
        return lastScene;
    }
}
