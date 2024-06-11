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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class gameViewer {

    @FXML
    protected ScrollPane scrollPane;
    protected MyGames games;
    protected Vue vueMosaique;
    protected Vue vueListe;
    protected GameGrid gameGrid;
    protected Vue vueActuelle ;
    protected Scene thisScene;

    public gameViewer(){
        games = new MyGames();
        vueMosaique = new VueMosaique();
        vueListe = new VueListe();
        gameGrid = new GameGrid(vueMosaique);
        vueActuelle = gameGrid.getVue();
    }

    public JeuController openGameDetails(MouseEvent event) {
        try {
            Node source = (Node) event.getSource();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/jeu.fxml"));
            Scene scene = new Scene(loader.load());
            Game game = games.getAllGames().get(Integer.parseInt((source).getId()));

            JeuController control = loader.getController();
            control.chargerJeu(game);

            if (this instanceof AccueilController) {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(thisScene.getWindow());
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.setResizable(false);

                control.activateDropGamePane();

                stage.showAndWait();
            } else {
                control.setLastScene(thisScene);
                ((Stage) source.getScene().getWindow()).setScene(scene);
            }

            return control;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void switchView() {
        if (vueActuelle instanceof VueMosaique) {
            vueActuelle = vueListe;

        } else {
            vueActuelle = vueMosaique;
        }
        gameGrid.setVue(vueActuelle);
        fillView();
    }

    void fillView() {
        gameGrid.resetGrid();
        gameGrid.fill(games);
        gameGrid.applyPadding(10);

        scrollPane.setContent(gameGrid.getGrid());

        for (Node node : gameGrid.getGrid().getChildren()) {
            node.setOnMouseClicked(this::openGameDetails);
        }

        scrollPane.setContent(gameGrid.getGrid());
    }

    public Scene getScene() {
        return thisScene;
    }

    public void setScene(Scene scene) {
        thisScene = scene;
    }

    public MyGames getGames() {
        return games;
    }
}