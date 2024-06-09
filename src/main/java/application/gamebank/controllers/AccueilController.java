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

    private MyGames games;
    private ListeTags tags;
    private Persistence persistence = new PersistenceBySerialization();

    @FXML
    public void initialize() {
        persistence.load();
        //todo chargement sauvergarde
        fillView();
    }

    @Override
    public JeuController openGameDetails(MouseEvent event) {
        JeuController control = super.openGameDetails(event);
        control.activateDropGamePane();
        control.activateAddTagPane();
        //if(games.getAllGames().get(Integer.parseInt(((Node) event.getSource()).getId())))
        //todo voir si le jeu a au moins un tag
        control.activateDropTagPane();
        return control;
    }

    @FXML
    RechercheController startResearch(MouseEvent event) {
        try {
            Node source = (Node) event.getSource();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/recherche.fxml"));
            Scene scene = new Scene(loader.load());
            RechercheController control = loader.getController();
            control.setScene(scene);
            control.setLastScene(thisScene);
            ((Stage) thisScene.getWindow()).setScene(scene);
            return control;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}