package application.gamebank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupprimerJeuxController {

    @FXML // Texte contenant le nom du jeu à supprimé
    public Label game_name;

    // Variable définisant le fait de si oui ou non le jeu est supprimer
    protected boolean a_supprimer;

    /** S'éxecute lors du chargement de la fenêtre */
    @FXML
    void initialize() {
        a_supprimer = false;
    }

    /** S'éxectue lors du renoncement à la supprésion */
    @FXML
    void onCancelAction(MouseEvent event) {
        quit();
    }

    /** S'éxecute lors de la validation de supprésion */
    @FXML
    void onValideAction(MouseEvent event) {
        a_supprimer = true;
        quit();
    }

    /** Ferme la fenêtre */
    private void quit() {
        ((Stage) game_name.getScene().getWindow()).close();
    }
}
