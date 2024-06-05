package application.gamebank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NouveauTagController {

    // Taille maximum du nom du tag
    private final static int MAX_SIZE_TAG_NAME = 15;

    @FXML // Zone de texte pour définir le nom du tag
    private TextField nom_du_tag;

    @FXML // Bouton de validation
    private Pane bouton_de_validation;

    @FXML /** S'éxecute lors du chargement de cette fenêtre */
    void initialize() {
        bouton_de_validation.setDisable(true);
    }

    @FXML /** S'éxecute lors d'un clique sur le bouton valider*/
    void boutonValiderNouveauTag(MouseEvent event) {
        quit(((Pane) event.getSource()).getScene());
    }

    @FXML /** Mette a jour le fonctionnent du bouton de validation */
    void updateValidationButton(KeyEvent event) {
        bouton_de_validation.setDisable(tagNameIsRight());
    }

    @FXML /** Executer lors de la validation (exemple avec entrer) */
    void onValidationEvent(ActionEvent event) {
        quit(((TextField) event.getSource()).getScene());
    }

    /** Ferme la fenêtre */
    private void quit(Scene scene) {
        ((Stage) scene.getWindow()).close();
    }

    /** Renvoie VRAI si le tag donnée est plus grand que 2 caractère et plus petit que 15 caractères */
    private boolean tagNameIsRight() {
        return tagNameEmpty() || tagNameOversize();
    }

    /** Renvoie VRAI si le nom du tag est vide, sinon FAUX */
    private boolean tagNameEmpty() {
        return nom_du_tag.getText().isEmpty();
    }

    /** Renvoie VRAI si le nom du tag est plus GRAND que le maximum, sinon FAUX */
    private boolean tagNameOversize() {
        return nom_du_tag.getText().length() > MAX_SIZE_TAG_NAME;
    }
}