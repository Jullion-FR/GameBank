package application.gamebank.vue;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GridElement {
    private final Pane element;
    private ImageView gameImageHolder;
    private Label nameHolder;

    public GridElement(Pane element) {
        this.element = element;
    }

    public Pane getRoot() {
        return element;
    }

    public void add(Node node, int index) {
        element.getChildren().add(index, node);
    }

    public void add(Node node) {
        element.getChildren().add(node);
    }

    // méthode pour cloner l'objet
    public GridElement clone() {
        Pane newPane;
        if (this.element instanceof VBox) {
            newPane = new VBox();
        } else if (this.element instanceof HBox) {
            newPane = new HBox();
        } else {
            newPane = new Pane(); // Pour tout autre type de Pane
        }

        GridElement clonedElement = new GridElement(newPane);

        // Initialiser logoHolder et nameHolder dans le clone, mais ne pas ajouter d'éléments enfants existants
        if (this.gameImageHolder != null) {
            ImageView clonedLogoHolder = new ImageView(this.gameImageHolder.getImage());
            clonedElement.setGameImageHolder(clonedLogoHolder);
        }

        if (this.nameHolder != null) {
            Label clonedNameHolder = new Label(this.nameHolder.getText());
            clonedElement.setNameHolder(clonedNameHolder);
        }

        return clonedElement;
    }

    public ImageView getGameImageHolder() {
        return gameImageHolder;
    }

    public Label getNameHolder() {
        return nameHolder;
    }

    public void setNameHolder(Label nameHolder) {
        this.nameHolder = nameHolder;
        add(nameHolder);
    }

    public void setNameHolder(Label nameHolder, int index) {
        this.nameHolder = nameHolder;
        add(nameHolder, index);
    }

    public void setGameImageHolder(ImageView gameImageHolder) {
        this.gameImageHolder = gameImageHolder;
        add(gameImageHolder);
    }
    public void setLogoHolder(ImageView logoHolder, int index) {
        this.gameImageHolder = logoHolder;
        add(logoHolder, index);
    }
}