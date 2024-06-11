package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.persistence.Persistence;
import application.gamebank.persistence.PersistenceBySerialization;
import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import application.gamebank.tri.Tri;
import application.gamebank.tri.TriParDate;
import application.gamebank.tri.TriParNom;
import application.gamebank.tri.TriParNotes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController extends gameViewer {

    @FXML
    private AnchorPane root;

    @FXML
    private VBox tagConteneur;

    @FXML
    private TextField gameFilterTextField;

    @FXML
    private ChoiceBox<String> triChoiceBox;

    private Stage researchStage;
    private MyGames gamesSave;
    private Tri triSelectioner;
    private MyTags tags;
    private final Persistence persistence;

    public AccueilController(){
        super();
        tags = new MyTags();
        persistence = new PersistenceBySerialization(games, tags);
    }

    @FXML
    public void initialize() {
        persistence.load(); // Charge les données
        tags = persistence.getTags();
        games = persistence.getGames();
        researchStage = new Stage();
        triSelectioner = new TriParNom();
        addEndEvent();
        initChoiceBox();
        fillView();

        updateTags();
    }

    @FXML
    void filterGames(ActionEvent event) {
        String filterText = gameFilterTextField.getText().toLowerCase();

        if (filterText.isBlank() && gamesSave != null) {
            // Restaurer l'état initial des jeux
            games = gamesSave;
            fillView();
        } else {
            // Sauvegarder l'état initial des jeux seulement si gamesSave est null
            if (gamesSave == null) {
                gamesSave = new MyGames();
                for (Game game : games.getAllGames()) {
                    gamesSave.addGame(game);
                }
            }
            MyGames filteredGames = new MyGames();
            for (Game game : games.getAllGames()) {
                if (game.getName().toLowerCase().contains(filterText)) {
                    filteredGames.addGame(game);
                }
            }

            games = filteredGames;
            fillView();
        }
    }

    private void initChoiceBox() {
        triChoiceBox.getItems().add("Titre");
        triChoiceBox.getItems().add("Date de sortie");
        triChoiceBox.getItems().add("Note");
        triChoiceBox.getSelectionModel().select(0);

        triChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Titre" -> triSelectioner = new TriParNom();
                case "Date de sortie" -> triSelectioner = new TriParDate();
                case "Note" -> triSelectioner = new TriParNotes();
            }
            fillView();
        });
    }

    /** Sauvegarde les jeux enregistrer */
    public void onWindowClosed() {
        persistence.save();
        researchStage.close();
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
        if (!researchStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/recherche.fxml"));
                Scene scene = new Scene(loader.load());
                RechercheController control = loader.getController();
                control.setScene(scene);
                researchStage.setScene(scene);
                researchStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            researchStage.toFront();
            researchStage.requestFocus();
        }
    }

    @Override
    void fillView() {
        triSelectioner.tri(games);//faire des classes tri avec interface
        super.fillView();
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

        // Suppresion des fils du conteneur
        tagConteneur.getChildren().clear();

        // Recalcule de la hauteur du conteneur

        int n = tags.nbTag(); // Nombre de tag
        double s  = tagConteneur.getSpacing(); // Espace entre deux tag
        double x = Tag.HEIGHT; // Taille des tags


        tagConteneur.setPrefHeight(n*x + (n-1)*s);

        // Ajout des tag dans le conteneur
        for (Tag t : tags.getAllTags()) {
            tagConteneur.getChildren().add(t.createTagPane());
        }


        // TODO heu je suis perdu pour ça, au secours Julien
        System.out.println(tags);
    }
}