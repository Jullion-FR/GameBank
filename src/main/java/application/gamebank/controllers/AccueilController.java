package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.persistence.Persistence;
import application.gamebank.persistence.PersistenceBySerialization;
import application.gamebank.tags.ListeTags;
import application.gamebank.tags.Tag;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AccueilController extends gameViewer {
    private ListeTags tags;
    private Persistence persistence = new PersistenceBySerialization();

    @FXML
    public void initialize() {
        games = persistence.loadGames();
        //todo chargement sauvergarde
        vueMosaique.setMaxGridLength(3);
        fillView();
    }


    @FXML
    RechercheController startResearch(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/recherche.fxml"));
            Scene scene = new Scene(loader.load());
            RechercheController control = loader.getController();
            control.setScene(scene);
            control.setLastScene(thisScene);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            return control;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    void fillView() {
        games.triAlphabetique();
        super.fillView();
    }

    @FXML
    void createNewTag(MouseEvent event) {

    }
}