package application.gamebank.controllers;

import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import application.gamebank.games.MyGames;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import application.gamebank.vue.VueListe;
import application.gamebank.vue.VueMosaique;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    void backToLastWindow(MouseEvent event) {
        ((Stage) thisScene.getWindow()).setScene(lastScene);
    }
    public void setLastScene(Scene scene) {
        this.lastScene = scene;
    }

}
