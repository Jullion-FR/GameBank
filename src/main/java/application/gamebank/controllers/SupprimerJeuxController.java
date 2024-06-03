package application.gamebank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupprimerJeuxController {

    @FXML // Texte contenant le nom du jeu à supprimé
    public Label game_name;

    // Variable définisant le fait de si oui ou non le jeu est supprimer
    private boolean a_supprimer;

    @FXML /** S'éxecute lors du chargement de la fenêtre */
    void initialize() {
        a_supprimer = false;
    }

    @FXML /** S'éxectue lors du renoncement à la supprésion */
    void onCancelAction(MouseEvent event) {
        quit();
    }

    @FXML /** S'éxecute lors de la validation de supprésion */
    void onValideAction(MouseEvent event) {
        a_supprimer = true;
        quit();
    }

    /** Ferme la fenêtre */
    private void quit() {
        ((Stage) game_name.getScene().getWindow()).close();
    }
}
