package application.gamebank.vue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Vue {
    protected GridElement gridElement;
    abstract void createGridElement();

    public GridElement getGridElement() {
        return gridElement;
    }

    abstract void formatGameImageView(ImageView imageView);
    abstract void formatRoot(Pane root) ;
    abstract void formatNameLabel(Label nameLabel);

    protected void formatAll(Pane root, ImageView imageView, Label nameLabel) {
        formatGameImageView(imageView);
        formatNameLabel(nameLabel);
        formatRoot(root);
    }

}
