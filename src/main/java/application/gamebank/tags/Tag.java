package application.gamebank.tags;

import application.gamebank.Main;
import application.gamebank.games.Game;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tag implements Comparable<Tag>, Serializable {

    public static final double HEIGHT = 32.0;

    private String nom;
    public List<Game> games;

    public Tag(String nom) {
        this.nom = nom;
        this.games = new LinkedList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void delGame(Game game) {
        games.remove(game);
    }

    public List<Game> getAllGames() {
        return games;
    }

    public AnchorPane createTagPane() {
        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(23.0);
        anchorPane.setLayoutY(95.0);
        anchorPane.setPrefHeight(HEIGHT);
        anchorPane.setPrefWidth(125.0);
        anchorPane.setStyle("-fx-background-color:  #3F5A90; -fx-background-radius: 7;");

        // Create Label
        Label label = new Label(this.nom);
        label.setLayoutX(32.0);
        label.setLayoutY(4.0);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(17.0));

        // Create ImageView
        ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream("images/tag.png")));
        imageView.setFitHeight(18.0);
        imageView.setFitWidth(18.0);
        imageView.setLayoutX(8.0);
        imageView.setLayoutY(10.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Add Label and ImageView to AnchorPane
        anchorPane.getChildren().addAll(label, imageView);

//        anchorPane.setOnMouseClicked(this::onClicked);

        return anchorPane;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int compareTo(Tag other) {
        return this.nom.compareTo(other.nom);
    }

    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object tag) {
        if (!(tag instanceof Tag)) return false;
        return ((Tag) tag).nom.equals(this.nom);
    }
}
