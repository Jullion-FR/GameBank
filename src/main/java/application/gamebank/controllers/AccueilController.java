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
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        anchorPane.getChildren().add(gridPane);

        int column = 0;
        int row = 0;
        for (String jeu : liste) {

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

            Label label = new Label(jeu);
            label.setLayoutX(71.0);
            label.setLayoutY(12.0);
            pane.getChildren().add(label);

            gridPane.add(pane, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        // Définir le nouvel AnchorPane comme le contenu du ScrollPane
        scrollPane.setContent(anchorPane);
    }
}