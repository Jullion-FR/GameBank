package application.gamebank.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;



public class AccueilController {

    List<String> liste = Arrays.asList("Super Mario Bros.", "The " +
                    "Legend of Zelda", "Minecraft", "Fortnite", "Call of Duty",
            "World of Warcraft", "Among Us", "Assassin's Creed", "Borderlands",
            "Civilization", "Dark Souls", "Elder Scrolls", "Fallout",
            "Grand Theft Auto", "Halo", "Infamous", "Just Cause",
            "Zelda: Breath of the Wild");

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void passerVueListe(MouseEvent event) {

    }

    @FXML
    void passerVueMosaique(MouseEvent event) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(699.0);
        anchorPane.setPrefWidth(880.0);

        // Créer un nouveau GridPane et l'ajouter à l'AnchorPane
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(27.0);
        gridPane.setLayoutY(12.0);
        gridPane.setPrefHeight(689.0);
        gridPane.setPrefWidth(823.0);
        anchorPane.getChildren().add(gridPane);

        // Ajouter les jeux à la grille
        int column = 0;
        int row = 0;
        for (String jeu : liste) {
            // Créer un nouveau Pane pour le jeu
            Pane pane = new Pane();
            pane.setPrefHeight(149.0);
            pane.setPrefWidth(97.0);
            pane.setStyle("-fx-background-color: #9A5ACCBF; -fx-background-radius: 7;");

            // Ajouter un Label au Pane avec le nom du jeu
            Label label = new Label(jeu);
            label.setLayoutX(71.0);
            label.setLayoutY(12.0);
            pane.getChildren().add(label);

            // Ajouter le Pane au GridPane
            gridPane.add(pane, column, row);

            // Passer à la colonne suivante
            column++;
            // Si la colonne est 3, passer à la ligne suivante
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        // Définir le nouvel AnchorPane comme le contenu du ScrollPane
        scrollPane.setContent(anchorPane);
    }
}