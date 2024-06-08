package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.persistence.PersistenceBySerialization;
import application.gamebank.persistence.PersistenceModele;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AccueilController {

    private final PersistenceModele persistence = new PersistenceBySerialization();
    List<String> liste = Arrays.asList("Super Mario Bros.", "The " +
                    "Legend of Zelda", "Minecraft", "Fortnite", "Call of Duty",
            "World of Warcraft", "Among Us", "Assassin's Creed", "Borderlands",
            "Civilization", "Dark Souls", "Elder Scrolls", "Fallout",
            "Grand Theft Auto", "Halo");
    @FXML
    private GridPane gamesInGrid;
    @FXML
    private VBox gamesInListe;
    private MyGames games;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    void initialize() {
        games = persistence.load();
        // Chargement des données persistantes
    }

    void toogleVisibility() {
        gamesInGrid.setVisible(!gamesInGrid.isVisible());
        gamesInListe.setVisible(!gamesInListe.isVisible());
    }

    @FXML
    void passerVueListe(MouseEvent event) {
        toogleVisibility();
    }

    Pane makePane(Game game) {
        double width = 97.0;
        double height = 149.0;

        Pane pane = new Pane();
        pane.setPrefHeight(height);
        pane.setPrefWidth(width);
        pane.setMinHeight(height);
        pane.setMinWidth(width);
        pane.setMaxHeight(height);
        pane.setMaxWidth(width);

        pane.setStyle("-fx-background-color: #9A5ACCBF; -fx-background-radius: 7; -fx-padding: 10 50 10 50;");

        Label label = new Label(game.getName());
        label.setLayoutX(71.0);
        label.setLayoutY(12.0);
        pane.getChildren().add(label);
        return pane;
    }

    @FXML
    void passerVueMosaique(MouseEvent event) {
        toogleVisibility();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(699.0);
        anchorPane.setPrefWidth(880.0);

        // Créer un nouveau GridPane et l'ajouter à l'AnchorPane
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(27.0);
        gridPane.setLayoutY(12.0);
        gridPane.setPrefHeight(689.0);
        gridPane.setPrefWidth(823.0);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        anchorPane.getChildren().add(gridPane);

        int column = 0;
        int row = 0;

        for (String jeu : liste) {

            gridPane.add(makePane(new Game()), column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        // Définir le nouvel AnchorPane comme le contenu du ScrollPane
        scrollPane.setContent(anchorPane);
    }

    @FXML
    void onAddTag(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setResizable(false);
        FXMLLoader root = new FXMLLoader(Main.class.getResource("fxml/nouveauTag.fxml"));
        stage.setScene(new Scene(root.load()));
        stage.setTitle("Default title");
        stage.initModality(Modality.WINDOW_MODAL);

        stage.initOwner(((AnchorPane) event.getSource()).getScene().getWindow());
        stage.showAndWait();

        ((AnchorPane) event.getSource()).getChildren().add(new FXMLLoader(Main.class.getResource("fxml/test.fxml")).load());
    }

    @FXML
    void onAddGame(MouseEvent event) {

    }
}