package application.gamebank.vue;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GridElement {
    private final Pane root;
    private ImageView gameImageHolder;
    private Label nameHolder;
    private Label gameRating;
    public GridElement(Pane element) {
        this.root = element;
    }

    public Pane getRoot() {
        return root;
    }

    public void add(Node node, int index) {
        root.getChildren().add(index, node);
    }

    public void add(Node node) {
        add(node, root.getChildren().size());
    }

    // méthode pour cloner l'objet
    public GridElement clone() {
        try {
            GridElement clone = (GridElement) super.clone();
        } catch (CloneNotSupportedException sonarlintEstContent) {
            //
        }

        Pane newPane;
        if (this.root instanceof VBox) {
            newPane = new VBox();
        } else if (this.root instanceof HBox) {
            newPane = new HBox();
        } else {
            newPane = new Pane(); // Pour tout autre type de Pane
        }

        GridElement clonedElement = new GridElement(newPane);
        // Cloner tous les enfants
        for (Node node : this.root.getChildren()) {
            //Noeuds speciaux
            if (node.equals(this.gameImageHolder)) {
                ImageView clonedGameImageHolder = new ImageView(this.gameImageHolder.getImage());
                clonedElement.addGameImageHolder(clonedGameImageHolder);
            }
            else if (node.equals(this.nameHolder)) {
                Label clonedNameHolder = new Label(this.nameHolder.getText());
                clonedElement.addNameHolder(clonedNameHolder);
            }
            //Noeuds basiques
            else {
                clonedElement.add(cloneNode(node));
            }
        }




        return clonedElement;
    }

    private Node cloneNode(Node node) {
        if (node instanceof ImageView originalImageView) {
            return new ImageView(originalImageView.getImage());
        } else if (node instanceof Label originalLabel) {
            return new Label(originalLabel.getText());
        } else {
            // Pour d'autres types de nœuds, vous devez gérer chaque type individuellement
            // ou lancer une exception si vous ne souhaitez pas cloner ces types
            throw new UnsupportedOperationException("Cloning for this type of node is not supported");
        }
    }

    public ImageView getGameImageHolder() {
        return gameImageHolder;
    }

    public Label getNameHolder() {
        return nameHolder;
    }

    public void addNameHolder(Label nameHolder) {
        this.nameHolder = nameHolder;
        add(nameHolder);
    }
    public void addGameImageHolder(ImageView gameImageHolder)  {
        if (this.gameImageHolder == null) {
            this.gameImageHolder = gameImageHolder;
            add(gameImageHolder);
        }else {
            throw new UnsupportedOperationException("Special Item already initialized");
        }
    }
    public void addGameRating(Label gameRating) {
        if (this.gameRating == null) {
            this.gameRating = gameRating;
            add(gameRating);
        }else {
            throw new UnsupportedOperationException("Special Item already initialized");
        }
    }
}