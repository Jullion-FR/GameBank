package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RechercheController extends gameViewer {

    @FXML
    private TextField entry;

    private APIManager apiManager;
    @FXML
    public void initialize(){
        apiManager = new APIManager();
    }
    @FXML
    public void onAction() {
        String searchedText = entry.getText();

        if (searchedText.isBlank())
            return;

        System.out.println("Recherche en cours...");
        thisScene.setCursor(Cursor.WAIT);

        entry.clear();
        try {
            games = apiManager.setInformations(searchedText, vueActuelle.getMaxGridLength()*4);
        } catch (GameNotFoundException e) {
            scrollPane.setContent(new Label("Aucun résultat"));
            System.out.println("Rien trouvé");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        fillView();

        System.out.println("Done.");
        thisScene.setCursor(Cursor.DEFAULT);
    }

    @Override
    public JeuController openGameDetails(MouseEvent event){
        JeuController control = super.openGameDetails(event);
        if (Main.accueilController.games.getAllGames().contains(control.getGame())) {
            control.activateDropGamePane();
        }else{control.activateAddGamePane();}
        return control;
    }
    @FXML
    void closeWindow(MouseEvent event) {
        ((Stage) thisScene.getWindow()).close();
    }

}
