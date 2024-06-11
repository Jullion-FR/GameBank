package application.gamebank.controllers;

import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddTagController {

    @FXML
    private Pane bouton_de_validation;

    @FXML
    private ChoiceBox<Tag> liste_tags;

    private MyTags all_tags;
    private Tag result;

    @FXML
    void initialize() {
        all_tags = new MyTags();
    }

    public void setAll_tags(MyTags all_tags) {
        this.all_tags = all_tags;

        for (Tag tag : all_tags.getAllTags()) {
            liste_tags.getItems().add(tag);
        }

    }

    @FXML
    void boutonValiderAddTag(MouseEvent event) {
        result = liste_tags.getValue();
        quit(((Pane) event.getSource()).getScene());
    }

    public Tag getResult() {
        return result;
    }

    /**
     * Ferme la fenêtre
     */
    private void quit(Scene scene) {
        ((Stage) scene.getWindow()).close();
    }

}
