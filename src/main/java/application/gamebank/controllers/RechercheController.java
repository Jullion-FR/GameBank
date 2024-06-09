package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RechercheController extends gameViewer {

    @FXML
    private TextField entry;

    private APIManager apiManager;
    private Scene lastScene;
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
    }

    @Override
    public JeuController openGameDetails(MouseEvent event){
        JeuController control = super.openGameDetails(event);
        control.activateAddGamePane();
        return control;
    }
    @FXML
    void backToAccueil(MouseEvent event) {
        //Main.accueilController.fillView();
        ((Stage) thisScene.getWindow()).close();
    }
    public void setLastScene(Scene scene) {
        this.lastScene = scene;
    }

}
