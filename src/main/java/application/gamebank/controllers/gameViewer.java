package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.vue.GameGrid;
import application.gamebank.vue.Vue;
import application.gamebank.vue.VueListe;
import application.gamebank.vue.VueMosaique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class gameViewer {
    @FXML
    protected ScrollPane scrollPane;
    protected MyGames games = new MyGames();
    protected GameGrid gameGrid = new GameGrid();
    protected Vue vueActuelle = gameGrid.getVue();

    protected Scene thisScene;

    public JeuController openGameDetails(MouseEvent event) {
        try {
            Node source = (Node) event.getSource();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/jeu.fxml"));
            Scene scene = new Scene(loader.load());
            Game game = games.getAllGames().get(Integer.parseInt((source).getId()));
            JeuController control = loader.getController();
            control.setLastScene(thisScene);
            control.chargerJeu(game);
            ((Stage) thisScene.getWindow()).setScene(scene);
            return control;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void switchView(){
        if (vueActuelle instanceof VueMosaique) {
            vueActuelle = VueListe.VUE_LISTE;
        } else {
            vueActuelle = VueMosaique.VUE_MOSAIQUE;
        }
        gameGrid.setVue(vueActuelle);
        fillView();
    }

    void fillView() {
        gameGrid.resetGrid();
        gameGrid.fill(games, 4);
        gameGrid.applyPadding(10);

        scrollPane.setContent(gameGrid.getGrid());

        for (Node node : gameGrid.getGrid().getChildren()) {
            node.setOnMouseClicked(this::openGameDetails);
        }

        scrollPane.setContent(gameGrid.getGrid());
    }

    public void setScene(Scene scene) {
        thisScene = scene;
    }

    public Scene getScene() {
        return thisScene;
    }

    public MyGames getGames() {
        return games;
    }
}