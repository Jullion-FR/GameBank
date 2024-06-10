package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.persistence.Persistence;
import application.gamebank.persistence.PersistenceBySerialization;
import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController extends gameViewer {

    @FXML
    private AnchorPane root;

    private MyTags tags = new MyTags(); // Enregistre tout les tags dans un même objet
    private final Persistence persistence = new PersistenceBySerialization(games, tags);

    @FXML
    public void initialize() {
        persistence.load(); // Charge les données
        tags = persistence.getTags();
        games = persistence.getGames();
        addEndEvent();
        fillView();
    }

    /** Ajoute un écouteur d'événement de fermeture à la fenêtre */
    private void addEndEvent() {
        root.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        ((Stage) newWindow).setOnCloseRequest(event -> onWindowClosed());
                    }
                });
            }
        });
    }

    /** Sauvegarde les jeux enregistrer */
    public void onWindowClosed() {
        persistence.save();
    }

    @Override
    public JeuController openGameDetails(MouseEvent event) {
        JeuController control = super.openGameDetails(event);
        control.activateDropGamePane();
        control.activateAddTagPane();
        //if(games.getAllGames().get(Integer.parseInt(((Node) event.getSource()).getId())))
        //todo voir si le jeu a au moins un tag
        control.activateDropTagPane();
        return control;
    }

    @FXML
    void startResearch(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/recherche.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void fillView() {
        games.triAlphabetique();
        super.fillView();
    }

    @FXML
    void createNewTag() {

        try {
            Stage stage = new Stage();
            FXMLLoader root = new FXMLLoader(Main.class.getResource("fxml/nouveauTag.fxml"));
            stage.setScene(new Scene(root.load()));
            stage.setTitle("Game Bank - new Tag");
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();
            Tag tag = new Tag(((NouveauTagController) root.getController()).getTagName());
            tags.addTag(tag);

        } catch (IOException e) {
            System.err.println("Une erreur est survenue");
        }

        updateTags();
    }

    /** Actualise l'affichage des tags dans la page d'accueil */
    private void updateTags() {
        // TODO heu je suis perdu pour ça, au secours Julien
        System.out.println(tags);
    }
}