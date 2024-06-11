package application.gamebank.vue;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class Vue {
    protected GridElement gridElement;
    protected int maxGridLength = 1;

    public int getMaxGridLength() {
        return maxGridLength;
    }

    public void setMaxGridLength(int maxGridLength) {
        this.maxGridLength = maxGridLength;
    }

    abstract void createGridElement();

    public GridElement getGridElement() {
        return gridElement;
    }

    abstract void formatGameImageView(ImageView imageView);
    abstract void formatRoot(Pane root) ;
    abstract void formatNameLabel(Label nameLabel);

    protected int maxViewWidth = 875;

    protected void formatAll(Pane root, ImageView imageView, Label nameLabel) {
        formatGameImageView(imageView);
        formatNameLabel(nameLabel);
        formatRoot(root);
    }

    public int getMaxViewWidth() {
        return maxViewWidth;
    }

    public void setMaxViewWidth(int maxViewWidth) {
        this.maxViewWidth = maxViewWidth;
    }

}
