package application.gamebank.controllers;

import application.gamebank.tags.MyTags;
import application.gamebank.tags.Tag;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.gamebank.Main;

public class AddTagController {

    @FXML
    private Pane bouton_de_validation;

    @FXML
    private ChoiceBox<Tag> tagChoiceBox;

    private Tag result;

    @FXML
    void initialize() {
        MyTags all_tags = Main.accueilController.getTags();
        for (Tag tag : all_tags.getAllTags()) {
            tagChoiceBox.getItems().add(tag);
        }
    }



    @FXML
    void boutonValiderAddTag(MouseEvent event) {
        result = tagChoiceBox.getValue();
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
