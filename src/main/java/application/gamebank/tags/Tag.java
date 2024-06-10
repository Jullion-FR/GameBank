package application.gamebank.tags;

import application.gamebank.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tag implements Comparable<Tag> {

    private String nom;

    public Tag(String nom) {
        this.nom = nom;
    }

    public static AnchorPane createTagPane(Tag tag) {
        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(23.0);
        anchorPane.setLayoutY(95.0);
        anchorPane.setPrefHeight(32.0);
        anchorPane.setPrefWidth(125.0);
        anchorPane.setStyle("-fx-background-color: black; -fx-background-radius: 7;");

        // Create Label
        Label label = new Label(tag.nom);
        label.setLayoutX(32.0);
        label.setLayoutY(4.0);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(17.0));

        // Create ImageView
        ImageView imageView = new ImageView(new Image(String.valueOf(Main.class.getResource("/images/tag.png"))));
        imageView.setFitHeight(18.0);
        imageView.setFitWidth(18.0);
        imageView.setLayoutX(8.0);
        imageView.setLayoutY(10.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Add Label and ImageView to AnchorPane
        anchorPane.getChildren().addAll(label, imageView);

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
}
